import { useContext, useRef } from "react";
import { Button, Col, Form, Image, Row, Spinner } from "react-bootstrap";
import { MyDispatchContext, MyUserContext } from "../App";
import './styleUser.css';
import { useNavigate } from "react-router";

const UserInfor = () => {
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatchContext);
    const nav = useNavigate();

    const handleLogout = () => {
        dispatch({ type: 'logout' });
        // Redirect to home page after logout
        nav('/');
      };

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
            </div>
        </>
    );
}

export default UserInfor;