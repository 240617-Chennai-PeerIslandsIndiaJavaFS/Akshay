package org.example.models;

public class Milestones {
    private int milestoneId;
    private MilestoneName milestoneName;
    private String milestoneDescription;
    private int projectId;

    public enum MilestoneName {
        Start,
        Designing,
        Developinng,
        Integrating,
        Currently_Testing,
        Currently_Deployed,
        Maintenance,
        Documentation,
        Projectcompletion;

        public static MilestoneName fromString(String name) {
            try {
                return MilestoneName.valueOf(name.replace(" ", "_").replace("-", "_"));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Such a milestone does not exist " + name);
            }
        }
    }
    public Milestones() {}

    public Milestones(int milestoneId, MilestoneName milestoneName, String milestoneDescription, int projectId) {
        this.milestoneId = milestoneId;
        this.milestoneName = milestoneName;
        this.milestoneDescription = milestoneDescription;
        this.projectId = projectId;
    }

    public int getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(int milestoneId) {
        this.milestoneId = milestoneId;
    }

    public MilestoneName getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(MilestoneName milestoneName) {
        this.milestoneName = milestoneName;
    }

    public String getMilestoneDescription() {
        return milestoneDescription;
    }

    public void setMilestoneDescription(String milestoneDescription) {
        this.milestoneDescription = milestoneDescription;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}