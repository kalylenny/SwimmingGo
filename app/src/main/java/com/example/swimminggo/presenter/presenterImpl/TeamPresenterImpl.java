package com.example.swimminggo.presenter.presenterImpl;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.swimminggo.constant.URLConstant;
import com.example.swimminggo.models.Team;
import com.example.swimminggo.presenter.TeamPresenter;
import com.example.swimminggo.singleton.ListTeam;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.view.coach.fragment.TeamFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TeamPresenterImpl implements TeamPresenter {

    TeamFragment teamFragment;

    public TeamPresenterImpl(TeamFragment teamFragment) {
        this.teamFragment = teamFragment;
        AndroidNetworking.initialize(teamFragment.getContext());
    }

    @Override
    public void getListTeam() {
        AndroidNetworking.get(URLConstant.getInstance().URL_TEAM)
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")) {
                                JSONArray list = response.getJSONArray("team");
                                for (int i = 0; i < list.length(); i++) {
                                    ListTeam.getInstance().getListTeam().add(new Team(list.getJSONObject(i)));
                                }
                                teamFragment.setupRecyclerView();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    @Override
    public Team getTeamById(int teamId) {
        return null;
    }

    @Override
    public void deleteTeam(final int position) {
        Team teamOnDelete = ListTeam.getInstance().getListTeam().get(position);
        AndroidNetworking.get(URLConstant.getInstance().getUrlDeleteTeam(teamOnDelete.getTeamID()))
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")){
                                ListTeam.getInstance().getListTeam().remove(position);
                                teamFragment.doDeleteTeam(true, "Xóa thành công");
                            } else {
                                teamFragment.doDeleteTeam(false, "Lỗi");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    @Override
    public int updateTeam(int teamId, Team team) {
        return 0;
    }

    @Override
    public boolean onAddTeam(final Team team) {
        JSONObject teamObject = teamObject(team);
        AndroidNetworking.post(URLConstant.getInstance().URL_ADD_TEAM)
                .addJSONObjectBody(teamObject)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("success")) {
                        ListTeam.getInstance().getListTeam().add(new Team(response.getJSONObject("team")));
                        teamFragment.doAddTeam(true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {

            }
        });
        return true;
    }

    private JSONObject teamObject(Team team) {
        JSONObject result = new JSONObject();
        try {
            result.put("team_name", team.getTeamName());
            result.put("team_age", team.getTeamAge());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
