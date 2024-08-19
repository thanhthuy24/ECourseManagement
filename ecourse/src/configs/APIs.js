import axios from "axios";
import cookie from "react-cookies";

const BASE_URL = 'http://localhost:8080/ECourseWeb/api/';

export const endpoints = {
    'categories': '/categories',
    'courses': 'courses',
    'course': (courseId) => `courses/${courseId}`,
    'teachers': 'teachers',
    'teacher': (teacherId) => `teachers/${teacherId}`,
    'login': '/login',
    'current-user': '/current-user',
    'register': '/users',
    'update-user': '/update-user',
    'pay': '/pay'
}

export const authAPIs = () => {
    return axios.create({
        baseURL: BASE_URL,
        headers: {
            Authorization: cookie.load('token')
        }
    });
}

export default axios.create({
    baseURL: BASE_URL
})