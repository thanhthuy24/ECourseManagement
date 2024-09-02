import './styleHeader.css';

import { useContext, useEffect, useState } from "react";
import APIs, { endpoints } from "../configs/APIs";
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { faCartShopping, faHeart } from "@fortawesome/free-solid-svg-icons";
import { Link, useNavigate } from 'react-router-dom';
import { MyCartContext, MyUserContext } from '../App';
import { Badge, Image } from 'react-bootstrap';


const Header = () => {
    const user = useContext(MyUserContext);

    const [categories, setCategories] = useState([]);
    const [kw, setKw] = useState("");
    const nav = useNavigate();
    const [cartCounter, ] = useContext(MyCartContext);

    const [notifications, setNotifications] = useState([]);
//   const [open, setOpen] = useState(false);

    // useEffect(() => {
    //     if (socket) {
    //         socket.on("getNotification", (data) => {
    //             setNotifications((prev) => [...prev, data]);
    //         });
    //     }
    // }, [socket]);

    // console.log(notifications);

    const loadCates = async () => {
        let res = await APIs.get(endpoints['categories']);
        setCategories(res.data);
    }

    useEffect(() => {
        loadCates();
    }, [])

    const submit = (e) => {
        e.preventDefault();
        nav(`/?q=${kw}`);
    }

    return (
        <>
            <Navbar expand="lg" className="bg-body-tertiary backgroudColor">
                <Container fluid>
                    {/* <Navbar.Brand href="#" className="me-auto" >ECouse Academy</Navbar.Brand> */}
                    <Link to="/" className='nav-link font-size-header' >ECouse Academy</Link>
                    <Navbar.Toggle aria-controls="navbarScroll" />
                    <Navbar.Collapse id="navbarScroll">
                        <Form className="d-flex mx-auto" onSubmit={submit}>
                            <Form.Control
                                type="search"
                                placeholder="Search"
                                className="me-2 input-search-header"
                                aria-label="Search"
                                value={kw}
                                onChange={e => setKw(e.target.value)}
                                style={{ width: '500px'}}
                            />
                            <Button 
                                variant="outline-success " 
                                className="button-color font-size-header"
                                type='submit'
                            >Search</Button>
                        </Form>
                        <Nav
                            className="ms-auto my-2 my-lg-0"
                            style={{ maxHeight: '100px' }}
                            navbarScroll
                        >
                            <Link to="/cart" className='nav-link margin'>
                                <FontAwesomeIcon icon={faCartShopping} size="2x" color="#8EA7E9"/>
                                <Badge style={{marginLeft: "5px"}} className="bg bg-danger">{cartCounter}</Badge>
                            </Link>
                            <Link to="/my-receipts" className='nav-link margin'>
                                <FontAwesomeIcon icon={faHeart} size="2x" color="#8EA7E9"/>
                            </Link>
                            {user === null ? <>
                                <Link to="/login" className='nav-link margin font-size-header'>
                                   Login
                                </Link>
                            </>: 
                            <>
                                <Link to="/user" className='nav-link margin font-size-header'>
                                    <Image src={user.avatar} rounded style={{width: "30px"}}/> {user.username}
                                </Link>
                            </>}
                            
                        </Nav>
                    </Navbar.Collapse>
                    
                </Container>
                
            </Navbar>
        </>
        
    );
}

export default Header;