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
import UserCourseDetail from "./user/UserCourseDetail";
import Lessons from "./components/lessons/Lessons";
import TeacherHeader from "./layouts/HeaderTeacher";
import HomeTeacher from "./components/teachers/HomeTeacher";
import Assignments from "./components/assignments/Assignments";
import AssignmentUpdate from "./components/assignments/AssignmentUpdate";
import Questions from "./components/questions/Questions";
import AddQuestion from "./components/questions/AddQuestion";
import UpdateChoices from "./components/questions/UpdateChoices";
import Quiz from "./components/quiz/Quiz";
import AfterQuiz from "./components/quiz/AfterQuiz";
import Essay from "./components/quiz/Essay";
import CheckEssays from "./components/quiz/CheckEssays";

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

    const isTeacher = user?.role === 'ROLE_TEACHER';

    return (
        <BrowserRouter>
            <MyUserContext.Provider value={user}>
                <MyDispatchContext.Provider value={dispatch}>
                    <MyCartContext.Provider value={[cartCounter, cartDispatch]}>
                        {isTeacher ? 
                        <>
                            <TeacherHeader />
                            <Routes>
                                <Route path='/' element={<HomeTeacher />} />
                                <Route path='/lecturer/assignments/courses/:courseId' element={<Assignments />} />
                                <Route path='/lecturer/assignment/:assignmentId' element={<AssignmentUpdate />} />
                                <Route path='/questions/assignment/:assignmentId' element={<Questions />} />
                                <Route path='/choices' element={<UpdateChoices />} />
                                <Route path='/questions/assignments/:assignmentId' element={<AddQuestion />} />
                                <Route path="/user" element={<UserInfor />}/>
                                <Route path='/essays/question/:questionId' element={<CheckEssays  />} />
                            </Routes>
                        </> : 
                        <>
                            <Header/>
                            <Routes>
                                <Route path="/" element={<Courses />} />
                                <Route path="/courses/:id" element={<CourseDetail />} />

                                <Route path="/teachers" element={<Teacher />} />
                                <Route path="/teachers/:id" element={<TeacherDetail />} />

                                <Route path="/login" element={<Login />} />
                                <Route path="/register" element={<Register />}/>

                                <Route path="/user" element={<UserInfor />}/>
                                <Route path="/cart" element={<Cart />}/>
                                <Route path="/my-receipts" element={<MyCourses />}/>
                                <Route path="/receipt/:id" element={<UserCourseDetail />}/>
                                <Route path="/lessons/:courseId" element={<Lessons />}/>
                                <Route path='/questions/assignment/:assignmentId' element={<Quiz />} />
                                <Route path='/score' element={<AfterQuiz />} />
                                <Route path='/essays/assignment/:assignmentId' element={<Essay />} />
                            </Routes>
                        </>
                        }
                        
                        
                    </MyCartContext.Provider>
                </MyDispatchContext.Provider>
            </MyUserContext.Provider>
        </BrowserRouter>
    );
}

export default App;