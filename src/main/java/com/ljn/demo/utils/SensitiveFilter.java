package com.ljn.demo.utils;

import org.apache.commons.lang3.CharUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 步骤：
 * 1.定义前缀树的数据结构
 * 2.根据敏感词构造前缀树
 * 3.对敏感词进行过滤
 */
@Component
public class SensitiveFilter {
    private static final Logger logger = LoggerFactory.getLogger(SensitiveFilter.class);

    // 敏感词的替换文本
    private static final String REPLACTEXT = "***";
    // 根节点为空
    private final TrieNode root = new TrieNode();

    // 1.定义前缀树的数据结构
    private static class TrieNode {
        private boolean isLastTrieNode;
        private final Map<Character, TrieNode> children = new HashMap<>();

        public void addChild(Character c, TrieNode node) {
            children.put(c, node);
        }

        public TrieNode getChild(Character c) {
            return children.get(c);
        }

        public boolean isLastTrieNode() {
            return isLastTrieNode;
        }

        public void setLastTrieNode(boolean lastTrieNode) {
            isLastTrieNode = lastTrieNode;
        }
    }

    // 2.根据敏感词构造前缀树
    @PostConstruct
    public void init() {
        try (
                final InputStream is = this.getClass().getClassLoader().getResourceAsStream("sensitive-words.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        ) {
            String keyWord;
            while ((keyWord = reader.readLine()) != null) {
                this.addKeyWord(keyWord);
            }
        } catch (IOException e) {
            logger.error("加载敏感词文件失败: " + e.getMessage());
        }

    }
    // 向前缀树中添加敏感词
    private void addKeyWord(String keyWord) {
        TrieNode cur = root;
        for (int i = 0; i < keyWord.length(); i++) {
            char c = keyWord.charAt(i);
            TrieNode child = cur.getChild(c);
            if (child == null) {
                child = new TrieNode();
                cur.addChild(c, child);
            }
            cur = child;
            if (i == keyWord.length()-1) {
                child.setLastTrieNode(true);
            }
        }
    }

    // 3.对敏感词进行过滤
    public String filter(String text) {
        if (!StringUtils.hasLength(text)) {
            return text;
        }
        // 指针1指向前缀树
        TrieNode curNode = root;
        // 指针2指向文本
        int begin = 0;
        // 指针3在指针2的基础上进行移动
        int end = 0;
        StringBuilder res = new StringBuilder();
        while (begin < text.length()) {
            char c = text.charAt(end);
            if (isSpecialSymbol(c)) {
                if (curNode == root) {
                    res.append(c);
                    end = ++begin;
                } else {
                    end++;
                }
                continue;
            }
            TrieNode child = curNode.getChild(c);
            if (child == null) {
                res.append(c);
                end = ++begin;
                curNode = root;
            } else if (child.isLastTrieNode()) {
                res.append(REPLACTEXT);
                begin = ++end;
                curNode = root;
            } else {
                end++;
                curNode = child;
            }
        }
        return res.toString();
    }
    // 判断是否为符号
    private boolean isSpecialSymbol(Character c) {
        // 0x2E80~0x9FFF 是东亚文字范围
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }
}

