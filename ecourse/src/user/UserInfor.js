import { useContext, useEffect, useState } from "react";
import { Button, Card, Col, Form, Image, Row, Spinner } from "react-bootstrap";
import { MyDispatchContext, MyUserContext } from "../App";
import './styleUser.css';
import { useNavigate } from "react-router";
import { authAPIs, endpoints } from "../configs/APIs";
import { format } from "date-fns";
import Slider from "react-slick";
import Arrow from "../components/courses/Arrow";

const UserInfor = () => {
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatchContext);
    const nav = useNavigate();

    const [courses, setCourses] = useState([]);

    const loadUserCourses = async () => {
        let res = await authAPIs().get(endpoints['get-enrollment'](user.id));
        setCourses(res.data);
        console.log(res.data);
    }

    useEffect(() => {
        loadUserCourses();
    }, []);

    const handleLogout = () => {
        dispatch({ type: 'logout' });
        nav('/');
      };

    const cardSliderSettings1 = {
    dots: false,
    infinite: true,
    speed: 500,
    slidesToShow: 3, // Số lượng card hiện tại cùng lúc
    slidesToScroll: 1,
    autoplay: false,
    prevArrow: <Arrow type="prev" />, // Sử dụng mũi tên tùy chỉnh
    nextArrow: <Arrow type="next" />, // Sử dụng mũi tên tùy chỉnh
    };

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
    

    const handleClick = (courseId) => {
        // console.log(`${courseId}`);
        nav(`/lessons/${courseId}`);
    }

    return(
        <>
            <div className="container">
            <Row>
                <Col>
                    {user === null ? 
                        <Spinner animation="border" role="status">
                            <span className="visually-hidden">Loading...</span>
                        </Spinner>
                    : <>
                        <Form method="post">
                            <div className="d-flex" >
                                <Form.Group style={{margin: "10px"}} className="mb-3" controlId="controliInputFirstname">
                                    <Form.Label>First name: </Form.Label>
                                    <Form.Control type="text" placeholder="Enter firstname" 
                                    value={user.firstName} />
                                </Form.Group>
                                <Form.Group style={{margin: "10px"}} className="mb-3" controlId="controliInputLastname">
                                    <Form.Label>Last name: </Form.Label>
                                    <Form.Control type="text" placeholder="Enter lastname" 
                                    value={user.lastName} />
                                </Form.Group>
                            </div>
                            
                            <div className="d-flex" >
                                <Form.Group style={{margin: "10px"}} className="mb-3" controlId="controliInputEmail">
                                    <Form.Label className="form-label">Email: </Form.Label>
                                    <Form.Control type="email" placeholder="Enter email" 
                                    value={user.email} />
                                </Form.Group>
                                <Form.Group style={{margin: "10px"}} className="mb-3" controlId="controliInputPhoneNumber">
                                    <Form.Label>Phone number: </Form.Label>
                                    <Form.Control type="number" placeholder="Enter phone number" 
                                    value={user.phoneNumber} />
                                </Form.Group>
                            </div>
                            <div className="d-flex" >
                            <Form.Group style={{margin: "10px"}} className="mb-3" controlId="controliInputPhoneNumber">
                                <Form.Label>Username: </Form.Label>
                                <Form.Control type="text" placeholder="Enter username" 
                                value={user.username} />
                            </Form.Group>
                            <Form.Group style={{margin: "10px"}} className="mb-3" controlId="controliInputAvatar">
                                <Form.Label>Avatar</Form.Label>
                                <Form.Control accept=".png,.jpg" type="file" />
                            </Form.Group>
                            </div>
                            <Button type="submit" className="button-login">Update</Button>
                        </Form> 
                        <Button  className="button-logout" onClick={handleLogout}>Đăng xuất</Button>
                    </>    
                    }
                </Col>
                <Col className="margin">
                    <p className="form-label">Your avatar:</p>
                    <Image roundedCircle className="image" src={user.avatar} />
                </Col>
            </Row>

            <div>
            <Slider {...cardSliderSettings}>
                {courses.map((c) => (
                    <Card className="card" key={c.id}>
                        <Card.Img variant="top" src={c.courseId?.image} />
                        <Card.Body>
                            <Card.Title className="font-size-bold">{c.courseId?.name}</Card.Title>
                            <Card.Text>
                                Ngày đăng ký: {format(c.enrollmentDate, 'dd/MM/yyyy')}
                            </Card.Text>
                        </Card.Body>
                        <Card.Footer>
                            <Button
                                    variant="danger"
                                    className="btn"
                                    
                                    onClick={() => handleClick(c.courseId?.id)}
                                >
                                    Truy cập khóa học
                            </Button>
                        </Card.Footer>
                    </Card>
                ))}
            </Slider>
            </div>
        </div>
        </>
    );
}

export default UserInfor;