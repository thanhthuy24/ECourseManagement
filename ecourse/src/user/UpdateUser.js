import { useContext, useEffect, useState } from "react";
import { Button, Col, Form, Image, Row, Spinner } from "react-bootstrap";
import { authAPIs, endpoints } from "../configs/APIs";
import { toast, ToastContainer } from "react-toastify";
import { MyUserContext } from "../App";

const UpdateUser = () => {
    const user = useContext(MyUserContext);

    const [userUpdate, setUserUpdate] = useState('');

    const loadUserInfor = async () => {
        try {
            let res = authAPIs().get(endpoints['current-user']);
            setUserUpdate(res.data);
        } catch(err){
            console.error(err);
        }

    }

    useEffect(() => {
        loadUserInfor();
    }, [user]);

    const [formData, setFormData] = useState({
        firstName: user?.firstName || '',
        lastName: user?.lastName || '',
        email: user?.email || '',
        phoneNumber: user?.phoneNumber || '',
        username: user?.username || '',
        file: null,
    });

    const handleChange = (e) => {
        const { name, value, type, files } = e.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: type === 'file' ? files[0] : value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const data = new FormData();
        data.append('firstName', formData.firstName);
        data.append('lastName', formData.lastName);
        data.append('email', formData.email);
        data.append('phoneNumber', formData.phoneNumber);
        data.append('username', formData.username);
        data.append('id', user.id);
        if (formData.file) {
            data.append('file', formData.file);
        }
        // else
            // data.append('file')

        try {
            await authAPIs().post(endpoints['update-user'], data, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            });
            toast.success("User information updated successfully!");
        } catch (error) {
            toast.error("Failed to update user information. Please try again.");
        }
    };

    return (
        <>
        <ToastContainer/>
            <div className="container">
                <Row>
                    <Col>
                        {user === null ? 
                            <Spinner animation="border" role="status">
                                <span className="visually-hidden">Loading...</span>
                            </Spinner>
                        : <>
                            <Form onSubmit={handleSubmit} method="post">
                                <div className="d-flex">
                                    <Form.Group style={{ margin: "10px" }} className="mb-3" controlId="controliInputFirstname">
                                        <Form.Label>First name: </Form.Label>
                                        <Form.Control type="text" name="firstName" placeholder="Enter firstname" 
                                            value={formData.firstName} onChange={handleChange} />
                                    </Form.Group>
                                    <Form.Group style={{ margin: "10px" }} className="mb-3" controlId="controliInputLastname">
                                        <Form.Label>Last name: </Form.Label>
                                        <Form.Control type="text" name="lastName" placeholder="Enter lastname" 
                                            value={formData.lastName} onChange={handleChange} />
                                    </Form.Group>
                                </div>
                                
                                <div className="d-flex">
                                    <Form.Group style={{ margin: "10px" }} className="mb-3" controlId="controliInputEmail">
                                        <Form.Label className="form-label">Email: </Form.Label>
                                        <Form.Control type="email" name="email" placeholder="Enter email" 
                                            value={formData.email} onChange={handleChange} />
                                    </Form.Group>
                                    <Form.Group style={{ margin: "10px" }} className="mb-3" controlId="controliInputPhoneNumber">
                                        <Form.Label>Phone number: </Form.Label>
                                        <Form.Control type="number" name="phoneNumber" placeholder="Enter phone number" 
                                            value={formData.phoneNumber} onChange={handleChange} />
                                    </Form.Group>
                                </div>
                                <div className="d-flex">
                                    <Form.Group style={{ margin: "10px" }} className="mb-3" controlId="controliInputUsername">
                                        <Form.Label>Username: </Form.Label>
                                        <Form.Control type="text" name="username" placeholder="Enter username" 
                                            value={formData.username} onChange={handleChange} />
                                    </Form.Group>
                                    <Form.Group style={{ margin: "10px" }} className="mb-3" controlId="controliInputAvatar">
                                        <Form.Label>Avatar</Form.Label>
                                        <Form.Control accept=".png,.jpg" type="file" name="file" onChange={handleChange} />
                                    </Form.Group>
                                </div>
                                <Button type="submit" className="button-login">Update</Button>
                            </Form> 
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

export default UpdateUser;