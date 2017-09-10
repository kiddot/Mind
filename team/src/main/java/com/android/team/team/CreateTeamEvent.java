package com.android.team.team;

import com.android.common.bean.Team;

/**
 * Created by kiddo on 17-9-10.
 */

public class CreateTeamEvent {
    private Team team;

    public CreateTeamEvent(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
