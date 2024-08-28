import React, { useContext, useEffect, useState } from "react";
import { Button, Card, Col, Container, Navbar, Row } from "react-bootstrap";
import { Link, useNavigate, useSearchParams } from "react-router-dom";
import APIs, { endpoints } from "../../configs/APIs";
import { format } from 'date-fns';
import cookie from "react-cookies";
import { MyCartContext } from "../../App";
import { ToastContainer, toast } from 'react-toastify';

  import 'react-toastify/dist/ReactToastify.css';

const Courses = () => {
    const [courses, setCourses] = useState([]);
    const [q] = useSearchParams(); 
    const [page, setPage] = useState(1);
    const nav = useNavigate();
    const [, dispatch] = useContext(MyCartContext);
    const [categories, setCategories] = useState([]);

    const loadCates = async () => {
        let res = await APIs.get(endpoints['categories']);
        setCategories(res.data);
    }

    useEffect(() => {
        loadCates();
    }, [])

    const loadCourses = async () => {
        try {
            let url = `${endpoints['courses']}?page=${page}`;
            
            let cateId = q.get("cateId")

            if (cateId != null) {
                setPage(1);
                url = `${url}&cateId=${cateId}`;
            }

            let k = q.get("kw");
            if (k != null) {
                setPage(1);
                url = `${url}&q=${k}`;
            }

            let res = await APIs.get(url);
            if (page === 1)
                setCourses(res.data);
            else
            setCourses(current => [...current, ...res.data]); 

        } catch(err) {
            console.error(err);
        }
        
    }

    useEffect(() => {
        loadCourses();
    }, [q, page])

    const handleCardClick = (id) => {
        nav(`/courses/${id}`);
    };

    const addToCart = (p) => {
        let cart = cookie.load("cart") || null;
        if (cart === null)
            cart = {};
    
        if (!(p.id in cart)) {
            cart[p.id] = {
                "id": p.id,
                "name": p.name,
                "price": p.price,
                "quantity": 1
            };
            cookie.save("cart", cart);
            dispatch({
                "type": "update"
            })
            toast.success("Product added to cart!");
        } else {
            toast.error("Product is already in the cart and cannot be added again.");
        }  
        
      }
    return (
        <>
         <Navbar className="backgroudColor margin" fill variant="tabs"> 
                <Container>
                {categories.map(c => 
                    {
                        const url = `/?cateId=${c.id}`;    
                        return <Link key={c.id} to={url} className='nav-link font-size-header'> {c.name} </Link>
                    }
                    )}
                </Container>
            </Navbar>
            <div className="container">
                <Row>
                <ToastContainer />
                    {courses.map(t => (
                    <Col key={t.id} className="mb-4 d-flex" md={3} xs={12}>
                        <Card className="w-100" style={{ height: '550px' }} >
                        <Card.Img variant="top" src={t.image} className="square-img" style={{ height: '200px', objectFit: 'cover' }} />
                        <Card.Body className="d-flex flex-column">
                            <Card.Title className="font-size-bold">{t.name}</Card.Title>
                            <Card.Title style={{ color: "#68A7AD" }}>
                                Ngày tạo: {format(t.createdDate, 'dd/MM/yyyy')}
                                </Card.Title>
                            <Card.Text className="flex-grow-1">
                            {t.description}
                            </Card.Text>
                            
                        </Card.Body>
                        <Card.Footer 
                                
                                className="d-flex" 
                                style={{ justifyContent: "space-around" }}>
                            <Button onClick={() => handleCardClick(t.id)} className="nav-link button-color font-size-header design-button">More details</Button>
                            <Button onClick={() => addToCart(t)} className="nav-link button-color font-size-header design-button">Add to card</Button>
                        </Card.Footer>
                        </Card>
                       
                    </Col>
                    ))}
                </Row>
            </div>
        </>
    );
}

export default Courses;