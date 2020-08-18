export default {
    TITLE: 'SSAFY 유기견입양 블로그',
    SERVER_URL : "http://localhost:8080",
    URL_TYPE: {
        MAIN : "main",
        USER: {
            LOGIN: 'login',
            JOIN: 'join',
            PROFILE:'profile', 
            UPDATE:'update',
            JOINSUCCESS: 'joinSuccess',
            LIKE : 'like',
            SURVEY : 'survey',
            MANAGER : 'manager',
            ADOPTIONDETAIL:'adoptiondetail'
        },
        POST: {
            LIST:"list",
            DETAIL: 'detail'
        },
        ADOPTIONPOST:{
            ADOPTLIST: "adoptList",
            ADOPTCREATE: "adoptCreate",
            ADOPTDETAIL: "adoptDetail",
            ADOPTUPDATE: "adoptUpdate"
        },
        LOST: {
            LOSTLIST:'lost',
            LOSTDETAIL:'lostDetail',
        },
    }
}