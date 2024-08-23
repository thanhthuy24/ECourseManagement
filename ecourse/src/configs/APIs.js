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
    'pay': '/pay',
    'my-receipts': (userId) => `receipts/user/${userId}`,
    'my-courses': (receiptId) => `receipt/${receiptId}`,
    'lessons': (coursesId) => `lessons/${coursesId}`,
    'videos': '/videos',
    'addCompleted': '/addVideoComplete',
    'videosCompleted': (userId) => `/videosCompleted/${userId}`,
    'progress': (courseId, userId) => `/course/${courseId}/user/${userId}`,
    'delete-cart': (cartId) => `cart/${cartId}`,

    'courses-by-teacher': (teacherId) => `/courses/teacher/${teacherId}`,
    'assignment-by-course': (courseId) => `/lecturer/assignments/courses/${courseId}`
    // 'videos': (lessonId) => `videos/${lessonId}`

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