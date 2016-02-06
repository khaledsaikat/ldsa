package de.due.ldsa;

import de.due.ldsa.model.InterestKind;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  Romina
 */
public class ModelUtils {
    public static void addInterestKind(List<InterestKind> kinds, InterestKind ik) {
        if (!kinds.contains(ik)) {
            kinds.add(ik);
        }
    }

    public static void removeInterestKind(List<InterestKind> kinds, InterestKind ik) {
        if (kinds.contains(ik)) {
            kinds.remove(ik);
        }
    }

    public static boolean isInterestKind(List<InterestKind> kinds, InterestKind ik) {
        return kinds.contains(ik);
    }

    public static boolean checkValidInterestKinds(List<InterestKind> kinds) {
        //TODO: check for other contradictions
        return !(kinds.contains(InterestKind.ALC_DRINK) && kinds.contains(InterestKind.NON_ALC_DRINK));
    }
}
