import { Button, Col, Form, Image, Row } from "react-bootstrap";
import './styleLogin.css';
import loginImage from './login.png';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faGoogle, faFacebook } from "@fortawesome/free-brands-svg-icons";
import { useContext, useState } from "react";
import APIs, { authAPIs, endpoints } from "../../configs/APIs";
import cookie from "react-cookies";
import { MyDispatchContext, MyUserContext } from "../../App";
import { Navigate } from "react-router";
import { Link } from "react-router-dom";

const Login = () => {
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatchContext);

    const [userName, setUserName] = useState();
    const [passWord, setPassWord] = useState();

    const loadLogin = async (e) => {
        e.preventDefault();

        let res = await APIs.post(endpoints['login'],
            {
                "username": userName,
                "password": passWord
            });
        cookie.save("token", res.data);

        let user = await authAPIs().get(endpoints['current-user']);
        cookie.save("user", user.data);
    
        dispatch({
            "type": "login",
            "payload": user.data
        })
    }

    if(user != null){
        return <Navigate to="/" />
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
                            <Link to="/" className="nav-link" style={{textAlign: "right", color: "red"}}>Forget password?</Link>
                            <Button type="submit" className="button-login button-sign-gg-fb">Sign in</Button>
                            
                            <div className="d-flex" style={{justifyContent: "center", margin: "5px"}}>
                                <p style={{margin: "3px"}}>Don't have an account yet? </p>
                                <Link to="/register" className="nav-link" style={{color: "blue", fontWeight: "bold", margin: "3px"}}>Sign up</Link>
                            </div>
                            
                        </Form>
                    </Col>
                </Row>
            </div>
        </>
    );
}

export default Login;