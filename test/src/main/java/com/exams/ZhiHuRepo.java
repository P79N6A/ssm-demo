package com.exams;

import java.io.Serializable;

import us.codecraft.webmagic.model.annotation.ExtractBy;

/**
 * @author fangcong on 2018/8/23.
 */
public class ZhiHuRepo implements Serializable {

    @ExtractBy(value = "//h1[@class='QuestionHeader-title']/text()", notNull = true)
    private String title;

    @ExtractBy(value = "//div[@class='QuestionRichText']/tidyText()")
    private String question;

    @ExtractBy(value = "//div[@class='QuestionAnswer-content']/tidyText()")
    private String answer;

    public ZhiHuRepo() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return "ZhiHuRepo{" +
            "title='" + title + '\'' +
            ", question='" + question + '\'' +
            ", answer='" + answer + '\'' +
            '}';
    }
}
