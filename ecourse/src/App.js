import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./layouts/Header";

import 'bootstrap/dist/css/bootstrap.min.css';
import Teacher from "./components/teachers/Teacher";
import TeacherDetail from "./components/teachers/TeacherDetail";
import Courses from "./components/courses/Courses";
import Login from "./components/login/Login";
import MyUserReducer from "./reducers/MyUserReducer";
import { createContext, useReducer } from "react";
import Register from "./components/login/Register";
import UserInfor from "./user/UserInfor";
import MyCourses from "./user/MyCourses";
import CourseDetail from "./components/courses/CourseDetail";
import cookie from "react-cookies";
import MyCartReducer from "./reducers/MyCartReducer";
import Cart from "./components/cart/Cart";

export const MyUserContext = createContext();
export const MyDispatchContext = createContext();
export const MyCartContext = createContext();

const count = () => {
    let cart = cookie.load("cart") || null;
    if (cart !== null) {
        let totalQuantity = 0;
        for (let c of Object.values(cart)) 
            totalQuantity += c.quantity;
  
      return totalQuantity;
    }
    return 0;
  };

const App = () => {
    const [user, dispatch] = useReducer(MyUserReducer, cookie.load("user") || null);
    const [cartCounter, cartDispatch] = useReducer(MyCartReducer, count());

    return (
        <BrowserRouter>
            <MyUserContext.Provider value={user}>
                <MyDispatchContext.Provider value={dispatch}>
                    <MyCartContext.Provider value={[cartCounter, cartDispatch]}>
                        <Header />
                        <Routes>
                            <Route path="/" element={<Courses />} />
                            <Route path="/courses/:id" element={<CourseDetail />} />
                            <Route path="/login" element={<Login />} />
                            <Route path="/teachers" element={<Teacher />} />
                            <Route path="/teachers/:id" element={<TeacherDetail />} />
                            <Route path="/register" element={<Register />}/>
                            <Route path="/user" element={<UserInfor />}/>
                            <Route path="/mycourses" element={<MyCourses />}/>
                            <Route path="/cart" element={<Cart />}/>
                        </Routes>
                    </MyCartContext.Provider>
                </MyDispatchContext.Provider>
            </MyUserContext.Provider>
        </BrowserRouter>
    );
}

export default App;