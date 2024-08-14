import { Button, Col, Form, Image, Row } from "react-bootstrap";
import './styleLogin.css';
import loginImage from './login.png';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faGoogle, faFacebook } from "@fortawesome/free-brands-svg-icons";
import { useState } from "react";
import APIs, { endpoints } from "../../configs/APIs";

const Login = () => {

    const [userName, setUserName] = useState();
    const [passWord, setPassWord] = useState();

    const loadLogin = async (e) => {
        e.preventDefault();

        let res = await APIs.post(endpoints['login'],
            {
                "username": userName,
                "password": passWord
            });
            console.info(res.data);
    }

    return(
        <>
            <div className="container">
                <Row>
                    <Col>
                        <Image className="image margin" src={loginImage} />
                    </Col>
                    <Col>
                        <p className="text-sign-in margin" >Sign in</p>
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
                        <Form onSubmit={loadLogin} method="post"> 
                            <Form.Group className="mb-3" controlId="controliInputUsername">
                                <Form.Label>Username: </Form.Label>
                                <Form.Control type="text" placeholder="Enter username" value={userName} onChange={e => setUserName(e.target.value)}/>
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="controliInputPassword">
                                <Form.Label>Password</Form.Label>
                                <Form.Control type="password" placeholder="Password" value={passWord} onChange={e => setPassWord(e.target.value)}/>
                            </Form.Group>
                            <Button type="submit" className="button-login">Sign in</Button>
                        </Form>
                    </Col>
                </Row>
            </div>
        </>
    );
}

export default Login;