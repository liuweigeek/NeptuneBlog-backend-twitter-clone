package com.scott.neptune.tweetserver.service.impl;

import com.scott.neptune.tweetserver.convertor.TweetConvertor;
import com.scott.neptune.tweetserver.repository.TweetRepository;
import com.scott.neptune.tweetserver.service.IReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: scott
 * @Email: <a href="mailto:liuweigeek@outlook.com">Scott Lau</a>
 * @Date: 2020/8/23 23:04
 * @Description: Reply tweet
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Service
public class ReplyServiceImpl implements IReplyService {

    private final TweetRepository tweetRepository;
    private final TweetConvertor tweetConvertor;
}
