package com.quiz.controller;

import java.io.IOException;
import java.util.List;
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

        for (Question question : questions) {
            String userAnswer = request.getParameter("question_" + question.getId());
            if (userAnswer != null) {
                // Find the correct option id
                int correctOptionId = -1;
                for(var option : question.getOptions()){
                    if(option.isCorrect()){
                        correctOptionId = option.getId();
                        break;
                    }
                }
                // Check if the user's selected option is the correct one
                if (Integer.parseInt(userAnswer) == correctOptionId) {
                    score++;
                }
            }
        }
        
        User user = (User) request.getSession().getAttribute("currentUser");
        resultDao.saveResult(user.getId(), quizId, score);

        request.setAttribute("score", score);
        request.setAttribute("totalQuestions", questions.size());
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("results.jsp");
        dispatcher.forward(request, response);
    }
}