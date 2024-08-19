import { faFacebook, faGoogle } from "@fortawesome/free-brands-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Alert, Button, Col, Form, Image, Row } from "react-bootstrap";
import loginImage from './login.png';
import { Link, useNavigate } from "react-router-dom";
import { useRef, useState } from "react";
import APIs, { endpoints } from "../../configs/APIs";

const Register = () => {
    const [user, setUser] = useState({});
    const avatar = useRef();
    const [err, setErr] = useState();
    const nav = useNavigate();

    const loadRegister = async (e) =>{
        e.preventDefault();
        if(user.password !== user.confirm) {
            setErr("Password's not matched")
        }
        else {
            let form = new FormData();
            for (let key in user)
                if (key !== 'confirm') {
                    form.append(key, user[key]);
                }
            if(avatar.current.files.length > 0){
                form.append("avatar", avatar.current.files[0]);
            }

            let res = await APIs.post(endpoints['register'], form, {
                headers: {
                    'Content-Type': "multipart/form-data"
                }
            });
            console.info(res.data);
            if (res.status === 201){
                nav("/login");
            }
        }

    }

    const change = (e, field) => {
        setUser({...user, [field]: e.target.value});
    }

    return (
        <>
            <div className="container">
                <Row>
                    <Col>
                        <Image className="image margin" src={loginImage} />
                    </Col>
                    <Col>
                        <p className="text-sign-in margin" >Sign up</p>
                        <p style={{textAlign: "center"}}>Welcome back! You've been missed!</p>
                        <div className="d-flex button-sign-gg-fb">
                        <Button>
                            <FontAwesomeIcon style={{marginRight: "10px"}} icon={faGoogle} />
                            Sign in with Google
                        </Button>
                        <Button>
                        <FontAwesomeIcon style={{marginRight: "10px"}}  icon={faFacebook} />
                            Sign in with FaceBook
                        </Button>
                        </div>
                        <Form method="post" onSubmit={loadRegister}> 
                            <Form.Group className="mb-3" controlId="controliInputFirstname">
                                <Form.Label>First name: </Form.Label>
                                <Form.Control type="text" placeholder="Enter firstname" 
                                 value={user.firstName} onChange={e => change(e, "firstName")} />
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="controliInputLastname">
                                <Form.Label>Last name: </Form.Label>
                                <Form.Control type="text" placeholder="Enter lastname" 
                                value={user.lastName} onChange={e => change(e, "lastName")} />
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="controliInputEmail">
                                <Form.Label>Email: </Form.Label>
                                <Form.Control type="email" placeholder="Enter email" 
                                value={user.email} onChange={e => change(e, "email")} />
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="controliInputPhoneNumber">
                                <Form.Label>Phone number: </Form.Label>
                                <Form.Control type="number" placeholder="Enter phone number" 
                                value={user.phoneNumber} onChange={e => change(e, "phoneNumber")} />
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="controliInputUsername">
                                <Form.Label>Username: </Form.Label>
                                <Form.Control type="text" placeholder="Enter username" 
                                value={user.username} onChange={e => change(e, "username")} />
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="controliInputPassword">
                                <Form.Label>Password: </Form.Label>
                                <Form.Control type="password" placeholder="Password" 
                                value={user.password} onChange={e => change(e, "password")} />
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="controliInputConfirmPassword">
                                <Form.Label>Confirm password: </Form.Label>
                                <Form.Control type="password" placeholder="Password" 
                                 value={user.confirm} onChange={e => change(e, "confirm")} />
                            </Form.Group>
                            {err && <Alert variant="danger">Password's not matched</Alert>}
                            <Form.Group className="mb-3" controlId="controliInputAvatar">
                                <Form.Label>Avatar</Form.Label>
                                <Form.Control accept=".png,.jpg" type="file" ref={avatar}   />
                            </Form.Group>
                            <Form.Group controlId="exampleForm.ControlTextarea5">
                                <Button type="submit" className="button-login">Sign up</Button>
                            </Form.Group>
                            <div className="d-flex" style={{justifyContent: "center", margin: "5px"}}>
                                <p style={{margin: "3px"}}>You've an account? </p>
                                <Link to="/login" className="nav-link" style={{color: "blue", fontWeight: "bold", margin: "3px"}}>Sign in</Link>
                            </div>
                        </Form>
                    </Col>
                </Row>
            </div>
        </>
    );
}

export default Register;