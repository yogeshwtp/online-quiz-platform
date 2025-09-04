package com.quiz.controller;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quiz.dao.QuestionDao;
import com.quiz.dao.ResultDao;
import com.quiz.model.Question;
import com.quiz.model.User;
import com.quiz.model.Option;


@WebServlet("/quiz")
public class QuizServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private QuestionDao questionDao = new QuestionDao();
    private ResultDao resultDao = new ResultDao();

    // Handles starting the quiz
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        List<Question> questions = questionDao.getQuestionsByQuizId(quizId);
        
        request.setAttribute("questions", questions);
        request.setAttribute("quizId", quizId);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("quiz.jsp");
        dispatcher.forward(request, response);
    }
    
    // Handles submitting the quiz
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        List<Question> questions = questionDao.getQuestionsByQuizId(quizId);
        int score = 0;

        // A map to store the user's answers: Key = questionId, Value = selectedOptionId
        Map<Integer, Integer> userAnswers = new HashMap<>();
        
        // A map to store the correctness of each answer: Key = questionId, Value = boolean
        Map<Integer, Boolean> answerCorrectness = new HashMap<>();

        for (Question question : questions) {
            String userAnswerStr = request.getParameter("question_" + question.getId());
            if (userAnswerStr != null) {
                int userAnswerId = Integer.parseInt(userAnswerStr);
                userAnswers.put(question.getId(), userAnswerId);

                int correctOptionId = -1;
                for (Option option : question.getOptions()) {
                    if (option.isCorrect()) {
                        correctOptionId = option.getId();
                        break;
                    }
                }

                if (userAnswerId == correctOptionId) {
                    score++;
                    answerCorrectness.put(question.getId(), true);
                } else {
                    answerCorrectness.put(question.getId(), false);
                }
            }
        }
        
        // Save the result to the database (optional, but good to keep)
        User user = (User) request.getSession().getAttribute("currentUser");
        if (user != null) {
            resultDao.saveResult(user.getId(), quizId, score, questions.size());
        }

        // Pass all the necessary data back to the quiz.jsp page
        request.setAttribute("questions", questions);
        request.setAttribute("quizId", quizId);
        request.setAttribute("score", score);
        request.setAttribute("totalQuestions", questions.size());
        request.setAttribute("userAnswers", userAnswers);
        request.setAttribute("answerCorrectness", answerCorrectness);
        request.setAttribute("submitted", true); // A flag to indicate the quiz has been submitted

        RequestDispatcher dispatcher = request.getRequestDispatcher("quiz.jsp");
        dispatcher.forward(request, response);
    }
}