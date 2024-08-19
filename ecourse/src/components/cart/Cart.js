import { Alert, Button, Col, Row, Table } from "react-bootstrap";
import './styleCart.css';
import { useContext, useState } from "react";
import cookie from "react-cookies";
import { MyCartContext, MyUserContext } from "../../App";
import { authAPIs, endpoints } from "../../configs/APIs";
const Cart = () => {
    const [cart, setCart] = useState(cookie.load("cart") || null);
    const [, dispatch] = useContext(MyCartContext);
    const user = useContext(MyUserContext);
    const totalPrice = cart ? Object.values(cart).reduce((sum, c) => sum + c.price, 0) : 0;
    const loadPay = async () => {
        if (cart !== null) {
            let res = await authAPIs().post(endpoints["pay"], Object.values(cart));
            if (res.status === 201) {
              setCart([]);
              cookie.remove("cart");
              dispatch({ type: "paid" });
            }
        }
    };


    return (
        <>
            <div className="container">
            {cart===null?<Alert variant="danger">Không có sản phẩm nào trong giỏ</Alert>:
                <>
                    
                    <h1 className="font-size-cart" >GIỎ HÀNG</h1>
                    <Row>
                        <Col>
                            <h1>Số lượng khóa học trong cart</h1>
                            <Table>
                                <tr>
                                    <th>Id</th>
                                    <th>Tên khóa học</th>
                                    <th>Giá</th>
                                    <th></th>
                                </tr>
                                {Object.values(cart).map(c => 
                                    <tr key={c.id}>
                                        <td>{c.id}</td>
                                        <td>{c.name}</td>
                                        <td>{c.price}</td>
                                        <td>
                                            <Button style={{backgroundColor: "red"}}>&times;</Button>
                                        </td>
                                    </tr>)}
                            </Table>
                        </Col>
                        <Col>
                        <h1>Tổng tiền: {totalPrice.toLocaleString()} VND</h1>
                            {user !== null && (
                                <Button onClick={loadPay} className="btn btn-info">
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