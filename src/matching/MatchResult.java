package matching;

public class MatchResult {

    private final int lostItemId;
    private final int foundItemId;
    private final int matchScore;
    private final String matchStatus;

    public MatchResult(int lostItemId, int foundItemId,
                       int matchScore, String matchStatus) {

        if (lostItemId <= 0 || foundItemId <= 0) {
            throw new IllegalArgumentException("Item IDs must be positive.");
        }

        if (matchScore < 0 || matchScore > 100) {
            throw new IllegalArgumentException("Match score must be between 0 and 100.");
        }

        if (matchStatus == null || matchStatus.trim().isEmpty()) {
            throw new IllegalArgumentException("Match status cannot be empty.");
        }

        this.lostItemId = lostItemId;
        this.foundItemId = foundItemId;
        this.matchScore = matchScore;
        this.matchStatus = matchStatus.trim();
    }

    public int getLostItemId() {
        return lostItemId;
    }

    public int getFoundItemId() {
        return foundItemId;
    }

    public int getMatchScore() {
        return matchScore;
    }

    public String getMatchStatus() {
        return matchStatus;
    }
}
