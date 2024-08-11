import axios from "axios";

const BASE_URL = 'http://localhost:8080/ECourseWeb/api/';

export const endpoints = {
    'categories': '/categories',
    'courses': 'courses',
    'teachers': 'teachers',
    'teacher': (teacherId) => `teachers/${teacherId}`
}
export default axios.create({
    baseURL: BASE_URL
})