import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./layouts/Header";

import 'bootstrap/dist/css/bootstrap.min.css';
import Teacher from "./components/teachers/Teacher";
import TeacherDetail from "./components/teachers/TeacherDetail";
import Courses from "./components/courses/Courses";

const App = () => {
    return (
        <BrowserRouter>
            <Header />
            <Routes>
                <Route path="/" element={<Courses />} />
                <Route path="/teachers" element={<Teacher />} />
                <Route path="/teachers/:id" element={<TeacherDetail />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;