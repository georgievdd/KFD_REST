# api
## auth
- POST /auth/login #аторизация
- POST /auth/registration #регистрация
- POST /auth/refresh #обновить access по refresh
## questinnaire
- GET /questionnaire/my_answers #получить мои ответы
- GET /questionnaire #получить вопросы
- POST /questionnaire/answer/{id} #ответить на конкретный вопрос
- POST /questionnaire #создать анкету !Доступно только администратору
## user
- GET /user #получить список пользователей
- GET /user/{id} #получить пользователя
