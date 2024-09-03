import React, { useContext, useEffect, useState } from "react";
import { Button, Card, Col, Container, Form, Image, InputGroup, Navbar, Row } from "react-bootstrap";
import { Link, useNavigate, useSearchParams } from "react-router-dom";
import APIs, { authAPIs, endpoints } from "../../configs/APIs";
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
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStar } from "@fortawesome/free-solid-svg-icons";


const Courses = () => {
    const [courses, setCourses] = useState([]);
    const [courseSlider, setCourseSlider] = useState([]);
    const [q] = useSearchParams(); 
    const [page, setPage] = useState(1);
    const nav = useNavigate();
    const [, dispatch] = useContext(MyCartContext);
    const [categories, setCategories] = useState([]);
    const [countRate, setCountRate] = useState({});

    const [fromPrice, setFromPrice] = useState('');
    const [toPrice, setToPrice] = useState('');
    const [rating, setRating] = useState(null);


    const loadCates = async () => {
        let res = await APIs.get(endpoints['categories']);
        setCategories(res.data);
    }

    useEffect(() => {
        loadCates();
    }, [])

    const loadCourseSlider = async() => {
        try {
            let res = await APIs.get(endpoints['courses']);
            setCourseSlider(res.data); 

        } catch(err) {
            console.error(err);
        }
    }

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

            const params = new URLSearchParams();

            if (fromPrice) {
                params.append('fromPrice', fromPrice);
            }
            if (toPrice) {
                params.append('toPrice', toPrice);
            }

            if (rating) {
                params.append('rating', rating);
            }
    
            url = `${endpoints['courses']}?${params.toString()}`;

            let res = await APIs.get(url);
            if (page === 1)
                setCourses(res.data);
            else
            setCourses(current => [...current, ...res.data]); 

        } catch(err) {
            console.error(err);
        }
        
    }

    const [avg, setAvg] = useState({});

    const loadAvgRating = async (courseId) => {
        try {
            let res = await authAPIs().get(endpoints['avg-rating'](courseId));
            setAvg(prev => ({
                ...prev,
                [courseId]: res.data
            }));
        } catch(err) {
            console.error(err);
        }
    }

    const loadCountRate = async (courseId) => {
        try {
            let res = await authAPIs().get(endpoints['count-amount-rate'](courseId));
            setCountRate(prev => ({
                ...prev,
                [courseId]: res.data
            }));
            // console.log(res.data);
        } catch(err) {
            console.error(err);
        }
    }

    useEffect(() => {
        loadCourses();
        loadCourseSlider();
    }, [q, page]);
    
    useEffect(() => {
        if (courses.length > 0) {
            courses.forEach(c => {
                loadAvgRating(c.id);
                loadCountRate(c.id);
            });
        }
    }, [courses]);

    const handleCardClick = (id) => {
        nav(`/courses/${id}`);
    };

    const handleRatingChange = (event) => {
        setRating(event.target.value);
        setPage(1); // Đặt lại trang về 1 khi thay đổi đánh giá
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
        infinite: courseSlider.length > 3, // Disable infinite loop if less than 3 courses
        speed: 500,
        slidesToShow: Math.min(3, courseSlider.length), // Adjust number of slides shown
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
                {courseSlider.map((c) => (
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
                                <div
                                    className={`tag ${
                                        c.tag.name === "Beginner"
                                        ? "tag-beginner"
                                        : c.tag.name === "Intermediate"
                                        ? "tag-intermediate"
                                        : c.tag.name === "Advanced"
                                        ? "tag-advanced"
                                        : ""
                                    }`}
                                    >
                                    {c.tag.name}
                                </div>
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

            <div className="mt-5">
                <Row>
                    <Col sm={3}>
                    <Card>
                        <Card.Header>
                            Xếp hạng
                        </Card.Header>
                        <Card.Body>
                        <Form>
                    <Card.Text className="flex-grow-1">
                        <Form.Check
                            inline
                            label="Từ 5 sao"
                            name="ratingGroup"
                            type="radio"
                            id="rating5"
                            value="5"
                            onChange={handleRatingChange}
                            checked={rating === '5'}
                        />
                    </Card.Text>
                    <Card.Text className="flex-grow-1">
                        <Form.Check
                            inline
                            label="Từ 4 sao"
                            name="ratingGroup"
                            type="radio"
                            id="rating4"
                            value="4"
                            onChange={handleRatingChange}
                            checked={rating === '4'}
                        />
                    </Card.Text>
                    <Card.Text className="flex-grow-1">
                        <Form.Check
                            inline
                            label="Từ 3 sao"
                            name="ratingGroup"
                            type="radio"
                            id="rating3"
                            value="3"
                            onChange={handleRatingChange}
                            checked={rating === '3'}
                        />
                    </Card.Text>
                </Form>
                        </Card.Body>
                    </Card>

                        <Card>
                            <Card.Header>
                                Giá
                            </Card.Header>
                            <Card.Body>
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="inputGroup-sizing-default">
                                Từ giá:
                                </InputGroup.Text>
                                <Form.Control
                                type="number"
                                value={fromPrice}
                                onChange={(e) => setFromPrice(e.target.value)}
                                />
                            </InputGroup>
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="inputGroup-sizing-default">
                                Đến giá:
                                </InputGroup.Text>
                                <Form.Control
                                type="number"
                                value={toPrice}
                                onChange={(e) => setToPrice(e.target.value)}
                                />
                            </InputGroup>
                            <Button onClick={loadCourses}>Lọc</Button>
                            </Card.Body>
                        </Card>
                        <Card>
                            <Card.Header>
                                Thời gian
                            </Card.Header>
                            <Card.Body>
                                
                            </Card.Body>
                        </Card>
                    </Col>
                   
                    {courses.map(t => (
                    <Col key={t.id} className="mb-4 d-flex" md={3} xs={12}>
                        <Card className="w-100" style={{ height: '550px' }} >
                        <Card.Img variant="top" src={t.image} className="square-img" style={{ height: '200px', objectFit: 'cover' }} />
                        <Card.Body className="d-flex flex-column">
                            <Card.Title className="font-size-bold">{t.name}</Card.Title>
                            <Card.Title style={{ color: "#68A7AD" }}>
                                Ngày tạo: {format(t.createdDate, 'dd/MM/yyyy')}
                            </Card.Title>
                            <Card.Text className="flex-grow-1" >
                                {avg[t.id] !== undefined ? avg[t.id].toLocaleString('en-US', { 
                                    minimumFractionDigits: 1, 
                                    maximumFractionDigits: 1 
                                }) : "Chưa có đánh giá"}
                                {[1, 2, 3, 4, 5].map((star) => (
                                            <span
                                                key={star}
                                                style={{
                                                    cursor: 'pointer',
                                                    color: star <= Math.round(avg[t.id]) ? 'gold' : 'gray',
                                                }}
                                            >
                                                <FontAwesomeIcon icon={faStar} />
                                            </span>
                                        ))}
                                    ({countRate[t.id]} đánh giá) 
                                <Card.Text style={{color: "gray", marginTop: "5px"}}>
                                    {t.teacher.user.username}
                                </Card.Text>
                                <Card.Text>
                                <span style={{ textDecoration: "line-through", color: "red" }}>
                                    Giá gốc: {t.price.toLocaleString()} VNĐ
                                </span>
                                <br />
                                <span style={{ color: "green", fontWeight: "bold" }}>
                                    Giá bán: {(t.price * (1 - t.discount / 100)).toLocaleString()} VNĐ
                                </span>
                                </Card.Text>
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
                {/* <Row className="mt-5">
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
                </Row> */}
            </div>
        </>
    );
}

export default Courses;