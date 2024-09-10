import { Alert, Button, Col, Row, Table } from "react-bootstrap";
import './styleCart.css';
import { useContext, useEffect, useState } from "react";
import cookie from "react-cookies";
import { MyCartContext, MyUserContext } from "../../App";
import { authAPIs, endpoints } from "../../configs/APIs";
import { ToastContainer, toast } from 'react-toastify';
import { v4 as uuidv4 } from "uuid";


const Cart = () => {
    const [cart, setCart] = useState(cookie.load("cart") || null);
    const [, dispatch] = useContext(MyCartContext);
    const user = useContext(MyUserContext);

    const totalPrice = cart
    ? Object.values(cart).reduce(
        (sum, c) => sum + c.price * (1 - c.discount / 100) * c.quantity,
        0
      )
    : 0;

    const removeFromCart = async (courseId) => {
        try {
            const updatedCart = { ...cart };
            delete updatedCart[courseId];
            setCart(updatedCart);
            cookie.save("cart", updatedCart);
        } catch (ex) {
            console.log("Error removing from cart:", ex);
        }
    }

    const createpayment = async () => {
      try {
        let newOrderId = uuidv4();
        let res = await authAPIs().post(endpoints["create-payment"], {
          orderId: newOrderId,
          amount: totalPrice,
          returnUrl: "http://localhost:3000/cart", // Chuyển hướng đến trang này sau khi thanh toán
        });
        console.log(res.data);
        const { payUrl } = res.data;
        if (payUrl) {
          window.location.href = payUrl; // Chuyển hướng tới trang thanh toán
        } else {
          console.error("payUrl không tồn tại");
        }
      } catch {
        console.error("Tạo thanh toán thất bại!!!");
      }
    };
  
    useEffect(() => {
      const updatePayment = async () => {
        try {
          let res2 = await authAPIs().post(
            endpoints["update-payment"],
            Object.values(cart)
          );
          if (res2.status === 200) {
            setCart([]);
            cookie.remove("cart");
            dispatch({ type: "paid" });
          }
        } catch {
          console.error("Cập nhật thanh toán thất bại");
        }
      };
  
      const queryParams = new URLSearchParams(window.location.search);
      const paymentStatus = queryParams.get("message"); // Kiểm tra trạng thái thanh toán từ URL
  
      if (paymentStatus === "Success") {
        updatePayment(); // Chỉ cập nhật thanh toán nếu thành công
      }
    }, [window.location.search]);



    return (
        <>
            <div className="container">
            <ToastContainer />
                {cart===null?<Alert variant="danger">Không có sản phẩm nào trong giỏ</Alert>:
                    <>
                    <h1 className="font-size-cart">GIỎ HÀNG</h1>
                        <Row>
                        <Col>
                            <h1>Số lượng khóa học trong cart</h1>
                            <Table striped bordered hover size="sm">
                            <thead>
                                <tr>
                                <th>Id</th>
                                <th>Tên khóa học</th>
                                <th>Giá</th>
                                <th>Giảm giá</th>
                                <th>Số lượng</th>
                                <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                {Object.values(cart).map((c) => (
                                <tr>
                                    <td>1</td>
                                    <td>{c.name}</td>
                                    <td>{c.price.toLocaleString()}</td>
                                    <td>{c.discount}%</td>
                                    <td>{c.quantity}</td>
                                    <td>
                                        <Button 
                                            style={{backgroundColor: "red"}}
                                            onClick={() => removeFromCart(c.id)}
                                            >&times;</Button>
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                            </Table>
                        </Col>
                        <Col>
                        <h1>Tổng tiền: {totalPrice.toLocaleString()} VND</h1>
                            {user !== null && (
                                <Button onClick={createpayment} className="btn btn-info">
                                Thanh toán
                                </Button>
                            )}
                        </Col>
                    </Row>
                </>
            }

                
            </div>
        </>
    );
}

export default Cart;