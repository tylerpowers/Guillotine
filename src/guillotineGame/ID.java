package guillotineGame;

/**
 * enum identification system for cards
 * @author rtp32
 */
public enum ID {

    // royal group
    KGL(Group.ROY, 5) {
        @Override
        public String toString() {
            return "King Louis XVI";
        }
    },
    MA(Group.ROY, 5) {
        @Override
        public String toString() {
            return "Marie Antoinette";
        }
    },
    REG(Group.ROY, 4) {
        @Override
        public String toString() {
            return "Regent";
        }
    },
    DUK(Group.ROY, 3) {
        @Override
        public String toString() {
            return "Duke";
        }
    },
    BAR(Group.ROY, 3) {
        @Override
        public String toString() {
            return "Baron";
        }
    },
    CT(Group.ROY, 2) {
        @Override
        public String toString() {
            return "Count";
        }
    },  // special case
    CTS(Group.ROY, 2) {
        @Override
        public String toString() {
            return "Countess";
        }
    },  // special case
    LRD(Group.ROY, 2) {
        @Override
        public String toString() {
            return "Lord";
        }
    },  // special case
    LDY(Group.ROY, 2) {
        @Override
        public String toString() {
            return "Lady";
        }
    },  // special case

    // church group
    CRD(Group.CHU, 5) {
        @Override
        public String toString() {
            return "Cardinal";
        }
    },
    ARB(Group.CHU, 4) {
        @Override
        public String toString() {
            return "Archbishop";
        }
    },
    NUN(Group.CHU, 3) {
        @Override
        public String toString() {
            return "Nun";
        }
    },
    BSH(Group.CHU, 2) {
        @Override
        public String toString() {
            return "Bishop";
        }
    },
    PR(Group.CHU, 1) {
        @Override
        public String toString() {
            return "Priest";
        }
    },
    HER(Group.CHU, 0) {
        @Override
        public String toString() {
            return "Heretic";
        }
    },  // special case

    // civic group
    GOV(Group.CIV, 4) {
        @Override
        public String toString() {
            return "Governor";
        }
    },
    MAY(Group.CIV, 3) {
        @Override
        public String toString() {
            return "Mayor";
        }
    },
    CLM(Group.CIV, 3) {
        @Override
        public String toString() {
            return "Councilman";
        }
    },
    JDG(Group.CIV, 2) {
        @Override
        public String toString() {
            return "Judge";
        }
    },
    TXC(Group.CIV, 0) {
        @Override
        public String toString() {
            return "Tax Collector";
        }
    },  // special case
    SHF(Group.CIV, 1) {
        @Override
        public String toString() {
            return "Sheriff";
        }
    },

    // military group
    PAL(Group.MIL, 0) {
        @Override
        public String toString() {
            return "Palace Guard";
        }
    },  // special case
    GEN(Group.MIL, 4) {
        @Override
        public String toString() {
            return "General";
        }
    },
    COL(Group.MIL, 3) {
        @Override
        public String toString() {
            return "Colonel";
        }
    },
    CPT(Group.MIL, 2) {
        @Override
        public String toString() {
            return "Captain";
        }
    },
    LUT(Group.MIL, 1) {
        @Override
        public String toString() {
            return "Lieutenant";
        }
    },

    // commoner group
    TRG(Group.COM, 0) {
        @Override
        public String toString() {
            return "Tragic Figure";
        }
    },  // special case
    HRO(Group.COM, -3) {
        @Override
        public String toString() {
            return "Heroic Figure";
        }
    },
    STD(Group.COM, -1) {
        @Override
        public String toString() {
            return "Student";
        }
    };


    // private field for group of card
    private final Group group;

    // private field for default number of points
    private final int points;


    /**
     * class constructor
     * @param group group of card
     * @param points default number of points for the card
     */
    ID(Group group, int points) {
        this.group = group;
        this.points = points;
    }


    /**
     * public accessor method for group
     * @return group of card
     */
    public Group getGroup() {
        return this.group;
    }


    /**
     * public accessor method for points
     * @return default number of points
     */
    public int getPoints() {
        return this.points;
    }

}
