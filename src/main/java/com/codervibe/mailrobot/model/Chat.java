package com.codervibe.mailrobot.model;


/**
 * (Chat)表实体类
 *
 * @author codervibe
 * @since 2021-11-07 20:18:27
 */

public class Chat {

    private Integer id;

    private String question;

    private String answer;

    public Chat() {
    }


    public Chat(Integer id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}



