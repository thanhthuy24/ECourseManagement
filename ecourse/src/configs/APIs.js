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
    "create-payment": "/create-payment",
    "update-payment": "/update-payment",

    'my-receipts': (userId) => `receipts/user/${userId}`,
    'my-courses': (receiptId) => `receipt/${receiptId}`,

    'get-enrollment': (userId) => `/enrollments/user/${userId}`,
    'count-user-course': (courseId) => `/enrollments/count/${courseId}`,
    'check-enrollments':(courseId, userId) => `/enrollments/course/${courseId}/user/${userId}`,

    'lessons': (coursesId) => `lessons/${coursesId}`,
    'get-all-lessons': '/lesson',

    'videos': '/videos',
    'addCompleted': '/addVideoComplete',
    'videosCompleted': (userId) => `/videosCompleted/${userId}`,
    'progress': (courseId, userId) => `/course/${courseId}/user/${userId}`,
    'delete-cart': (cartId) => `cart/${cartId}`,

    'courses-by-teacher': (teacherId) => `/courses/teacher/${teacherId}`,

    'assignment-by-course': (courseId) => `/lecturer/assignments/courses/${courseId}`,
    'assignment': (assignmentId) => `/lecturer/assignment/${assignmentId}`,

    'user-assignments': (courseId) => `/assignments/courses/${courseId}`,

    'questions': (assignmentId) => `/questions/assignment/${assignmentId}`,
    'question': (questionId) => `/questions/${questionId}`,
    'add-question': '/questions',

    'choices': (questionId) => `/choices/question/${questionId}`,
    'add-choice': `/choices`,
    'correct-choices': (questionId) => `choices/isCorrect/question/${questionId}`,

    'answer': '/answerchoices',

    'check-assignment-done': (userId) => `/answerchoices/user/${userId}`,
    'check-answerchoice':(userId, assignmentId) => `/answerchoices/user/${userId}/assignment/${assignmentId}`,

    'score': (assignmentId, userId) => `/score/assignment/${assignmentId}/user/${userId}`,
    'add-score-essay': '/score',
    
    'scores': (assignmentId, userId) => `/scores/assignment/${assignmentId}/user/${userId}`,
    'userDone':(assignmentId, userId) => `/userdone/assignment/${assignmentId}/user/${userId}`,
    'count-userDone': (assignmentId) => `/userdone/assignment/${assignmentId}`,

    'enrollment': (courseId) => `/enrollments/course/${courseId}`,
    'user-enrollment': (userId) => `/enrollments/user/${userId}`,

    'add-essay':'/essays',
    'essay': (assignmentId) => `/essays/assignment/${assignmentId}`,
    'check-essays': (questionId) => `/essays/question/${questionId}`,
    'essay-user-done': (userId) => `/essays/user/${userId}`,

    'rating-course': (courseId) => `/courseRating/course/${courseId}`,
    'check-rating': (userId, courseId) => `/courseRating/user/${userId}/course/${courseId}`,
    'avg-rating' :(courseId) =>  `/courseRating/avgCourse/${courseId}`,
    'rating-percent' : (rating, courseId) => `/courseRating/course/${courseId}/rating/${rating}`,
    'count-amount-rate':(courseId) => `/courseRating/count/course/${courseId}`,
    'get-list-ratings':(courseId) => `/courseRating/listRating/course/${courseId}`,

    'create-certificate': (courseId, userId) => `/certification/${courseId}/user/${userId}`,

    'send-notic':'/notifications',
    'notic-unseen':(userId) => `/notifications/${userId}`,
    'mark-notic':(notificationId) => `/notifications/mark-as-read/${notificationId}`,

    'tags': '/tags',

    "google-login": "/google"

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