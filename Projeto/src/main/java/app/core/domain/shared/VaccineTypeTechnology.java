package app.core.domain.shared;

/**
 * The enumerator that represents the existing vaccine type technologies.
 *
 * @author Group 40
 */
public enum VaccineTypeTechnology {
    /**
     * The technologies.
     */
    LIVE_ATTENUATED {
        @Override
        public String toString() {
            return "Live-attenuated vaccines";
        }
    },
    INACTIVATED {
        @Override
        public String toString() {
            return "Inactivated vaccines";
        }
    },
    SUBUNIT {
        @Override
        public String toString() {
            return "Subunit vaccines";
        }
    },
    TOXOID {
        @Override
        public String toString() {
            return "Toxoid vaccines";
        }
    },
    VIRAL_VECTOR {
        @Override
        public String toString() {
            return "Viral vector vaccines";
        }
    },
    MESSENGER_RNA {
        @Override
        public String toString() {
            return "Messenger RNA (mRNA) vaccines";
        }
    };

    /**
     * Returns the designation of the technology whose order is received by parameter.
     *
     * @param technologyOrder the order of technology.
     * @return the technology.
     */
    public static String technologyDesignation(int technologyOrder) {
        return VaccineTypeTechnology.values()[technologyOrder].toString();
    }
}
