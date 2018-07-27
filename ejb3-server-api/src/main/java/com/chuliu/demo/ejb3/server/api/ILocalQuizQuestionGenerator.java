package com.chuliu.demo.ejb3.server.api;

import com.chuliu.demo.ejb3.server.api.entities.LevelQuestion;

/**
 * Created by eiuhucl on 7/23/2018.
 */
public interface ILocalQuizQuestionGenerator {

    LevelQuestion generateQuestion(int level);
}
