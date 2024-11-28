package com.example.project.listeners;

import com.example.project.Member;
/**
 * @author BT&T
 * CS 460
 */
public interface MemberListener {
    /**
     * allows for a members listerner to be implemented later
     * @param member the user of the application
     */
    void onMemberClicked(Member member);
}
