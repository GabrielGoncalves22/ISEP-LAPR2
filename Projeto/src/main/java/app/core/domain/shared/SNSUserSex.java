package app.core.domain.shared;

/**
 * The enumerator that represents the available sex options for the SNS User.
 *
 * @author Group 40
 */
public enum SNSUserSex {
    /**
     * The sexes.
     */
    FEMALE {
        @Override
        public String toString() {
            return "Female";
        }
    },
    MALE {
        @Override
        public String toString() {
            return "Male";
        }
    };

    /**
     * Returns the designation of the sex whose order is received by parameter.
     *
     * @param sexOrder the order of sex between female and male.
     * @return the sex designation.
     */
    public static String sexDesignation(int sexOrder) {
        return SNSUserSex.values()[sexOrder].toString();
    }
}
