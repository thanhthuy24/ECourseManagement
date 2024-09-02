import React, { useContext, useEffect, useState } from "react";
import { Button, Card, Col, Container, Navbar, Row } from "react-bootstrap";
import { Link, useNavigate, useSearchParams } from "react-router-dom";
import APIs, { endpoints } from "../../configs/APIs";
import { format } from 'date-fns';
import cookie from "react-cookies";
import { MyCartContext } from "../../App";
import './styleCourse.css';
import Arrow from "./Arrow";
import { ToastContainer, toast } from 'react-toastify';
import Slider from "react-slick";
import Carousel from "./Carousel";
import 'react-toastify/dist/ReactToastify.css';

import "slick-carousel/slick/slick.css"; 
import "slick-carousel/slick/slick-theme.css";


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

    const getTagClass = (tagName) => {
        switch (tagName) {
          case "Beginner":
            return "tag-beginner";
          case "Intermediate":
            return "tag-intermediate";
          case "Master":
            return "tag-master";
          default:
            return "tag-default";
        }
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
                "quantity": 1,
                "discount": p.discount,
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

      const cardSliderSettings = {
        dots: false,
        infinite: courses.length > 3, // Disable infinite loop if less than 3 courses
        speed: 500,
        slidesToShow: Math.min(3, courses.length), // Adjust number of slides shown
        slidesToScroll: 1,
        autoplay: false,
        prevArrow: <Arrow type="prev" />, // Custom arrow
        nextArrow: <Arrow type="next" />, // Custom arrow
    };

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
                <ToastContainer/>
        <div className="container">
            <Carousel />
            <h2 style={{ fontWeight: "bold" }}>Được đề xuất cho bạn</h2>
            <Slider {...cardSliderSettings}>
                {courses.map((c) => (
                    <Card className="card" key={c.id}>
                        <Card.Img variant="top" src={c.image} />
                        <Card.Body>
                            <Card.Title>{c.name}</Card.Title>
                            <Card.Text>
                                <div>
                                {c.teacher.user.username}
                                </div>
                                <span style={{ textDecoration: "line-through", color: "red" }}>
                                {c.price.toLocaleString()} VNĐ
                                </span>
                                <br />
                                <span style={{ color: "green", fontWeight: "bold" }}>
                                {(c.price * (1 - c.discount / 100)).toLocaleString()} VNĐ
                                </span>
                                {/* <div className={`tag ${getTagClass(c.tag.name)}`}>
                                {c.tag.name}
                                </div> */}
                            </Card.Text>
                            <Button
                                variant="danger"
                                className="btn"
                                onClick={() => handleCardClick(c.id)}
                            >
                                Xem chi tiết
                            </Button>
                            <Button
                                variant="danger"
                                className="btn"
                                onClick={() => addToCart(c)}
                            >
                                Thêm vào giỏ hàng
                            </Button>
                        </Card.Body>    
                    </Card>
                ))}
            </Slider>
            </div>
            {/* <div className="container">
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
            </div> */}
        </>
    );
}

export default Courses;