CREATE TABLE players
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    date_of_birth   date NULL,
    player_position VARCHAR(255) NULL,
    team_id         BIGINT NULL,
    CONSTRAINT pk_players PRIMARY KEY (id)
);

CREATE TABLE teams
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_teams PRIMARY KEY (id)
);

ALTER TABLE players
    ADD CONSTRAINT FK_PLAYERS_ON_TEAM FOREIGN KEY (team_id) REFERENCES teams (id);