package com.example.swimminggo.presenter;

import com.example.swimminggo.models.Team;

import java.util.List;

public interface TeamPresenter {
    void getListTeam();
    Team getTeamById(int position);
    void deleteTeam(int teamId);
    int updateTeam(int teamId, Team team);
    boolean onAddTeam(Team team);
}
