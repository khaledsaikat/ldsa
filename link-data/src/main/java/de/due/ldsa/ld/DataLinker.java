/**
 * 
 */
package de.due.ldsa.ld;

import java.util.List;
import java.util.ArrayList;

import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.model.Comment;
import de.due.ldsa.model.HumanProfile;
import de.due.ldsa.model.ProfileFeed;

/**
 * @author Firas Sabbah
 *
 */
public class DataLinker {

	private Database databaseService;

	/**
	 * Link comments, followers, followed by, friends, relationships, other
	 * linked profiles.
	 */
	public DataLinker() {
		databaseService = DatabaseImpl.getInstance();
	}

	/**
	 * Link the HumanProfile data
	 * 
	 * @param humanProfile
	 */
	public void linkHumanProfileData(HumanProfile humanProfile) {
		linkComments(humanProfile);
		linkFollowedBy(humanProfile);
		linkFollows(humanProfile);
		linkFriends(humanProfile);
		LinkLinkedOtherSocialNetworkProfiles(humanProfile);
		linkProfileFeeds(humanProfile);
		linkRelationshipPersons(humanProfile);
	}

	/**
	 * Link Relationships with the HumanProfile.
	 * 
	 * @param humanProfile
	 */
	private void linkRelationshipPersons(HumanProfile humanProfile) {
		findRelationshipPersons(humanProfile);
		if (humanProfile.getRelationshipPersons() != null && humanProfile.getRelationshipPersons().size() > 0) {
			List<HumanProfile> relationshipPersons = new ArrayList<HumanProfile>();
			for (Long id : humanProfile.getRelationshipPersons()) {
				relationshipPersons.add(databaseService.getHumanProfile(id));
			}
			humanProfile.setRelationshipPersonsProfiles(relationshipPersons);
		}
	}

	/**
	 * Fill the relationships in HumanProfile.
	 * 
	 * @param humanProfile
	 */
	private void findRelationshipPersons(HumanProfile humanProfile) {
		if (humanProfile.getRelationshipPersons() == null || humanProfile.getRelationshipPersons().size() == 0) {
			ArrayList<Long> profiles = databaseService.getProfileRelationshipPersons(humanProfile);
			humanProfile.setAllCommentsId(profiles);
		}
	}

	/**
	 * Link ProfileFeeds with the HumanProfile.
	 * 
	 * @param humanProfile
	 */
	private void linkProfileFeeds(HumanProfile humanProfile) {
		findProfileFeedIds(humanProfile);
		if (humanProfile.getProfileFeedIds() != null && humanProfile.getProfileFeedIds().size() > 0) {
			List<ProfileFeed> profileFeeds = new ArrayList<ProfileFeed>();
			for (Long id : humanProfile.getProfileFeedIds()) {
				profileFeeds.add(databaseService.getProfileFeed(id));
			}
			humanProfile.setProfileFeeds(profileFeeds);
		}
	}

	/**
	 * Fill the ProfileFeeds in HumanProfile.
	 * 
	 * @param humanProfile
	 */
	private void findProfileFeedIds(HumanProfile humanProfile) {
		if (humanProfile.getProfileFeedIds() == null || humanProfile.getProfileFeedIds().size() == 0) {
			ArrayList<Long> feeds = databaseService.getProfileProfileFeeds(humanProfile);
			humanProfile.setProfileFeedIds(feeds);
		}
	}

	/**
	 * Link OtherLinkedProfiles with the HumanProfile.
	 * 
	 * @param humanProfile
	 */
	private void LinkLinkedOtherSocialNetworkProfiles(HumanProfile humanProfile) {
		findLinkedOtherSocialNetworkProfileIds(humanProfile);
		if (humanProfile.getLinkedOtherSocialNetworkProfileIds() != null
				&& humanProfile.getLinkedOtherSocialNetworkProfileIds().size() > 0) {

			List<HumanProfile> linkedOtherSocialNetworkProfile = new ArrayList<HumanProfile>();
			for (Long id : humanProfile.getLinkedOtherSocialNetworkProfileIds()) {
				linkedOtherSocialNetworkProfile.add(databaseService.getHumanProfile(id));
			}
			humanProfile.setLinkedOtherSocialNetworkProfiles(linkedOtherSocialNetworkProfile);
		}
	}

	/**
	 * Fill OtherLinkedProfiles in HumanProfile.
	 * 
	 * @param humanProfile
	 */
	private void findLinkedOtherSocialNetworkProfileIds(HumanProfile humanProfile) {
		if (humanProfile.getLinkedOtherSocialNetworkProfileIds() == null
				|| humanProfile.getLinkedOtherSocialNetworkProfileIds().size() == 0) {
			ArrayList<Long> profiles = databaseService.getProfileLinkedOtherSocialNetworkProfileIds(humanProfile);
			humanProfile.setAllCommentsId(profiles);
		}
	}

	/**
	 * Link Friends with HumanProfile.
	 * 
	 * @param humanProfile
	 */
	private void linkFriends(HumanProfile humanProfile) {
		findFriendIds(humanProfile);
		if (humanProfile.getFriendIds() != null && humanProfile.getFriendIds().size() > 0) {

			List<HumanProfile> Friends = new ArrayList<HumanProfile>();
			for (Long id : humanProfile.getFriendIds()) {
				Friends.add(databaseService.getHumanProfile(id));
			}
			humanProfile.setFriends(Friends);
		}
	}

	/**
	 * Fill Friends in HumanProfile.
	 * 
	 * @param humanProfile
	 */
	private void findFriendIds(HumanProfile humanProfile) {
		if (humanProfile.getFriendIds() == null || humanProfile.getFriendIds().size() == 0) {
			ArrayList<Long> profiles = databaseService.getProfileFriendsIds(humanProfile);
			humanProfile.setFriendIds(profiles);
		}
	}

	/**
	 * Link Follows List with HumanProfile.
	 * 
	 * @param humanProfile
	 */
	private void linkFollows(HumanProfile humanProfile) {
		findFollowsIds(humanProfile);

		if (humanProfile.getFollowsIds() != null && humanProfile.getFollowsIds().size() > 0) {
			List<HumanProfile> follows = new ArrayList<HumanProfile>();
			for (Long id : humanProfile.getFollowsIds()) {
				follows.add(databaseService.getHumanProfile(id));
			}
			humanProfile.setFollows(follows);
		}
	}

	/**
	 * Fill Follows in HumanProfile.
	 * 
	 * @param humanProfile
	 */
	private void findFollowsIds(HumanProfile humanProfile) {
		if (humanProfile.getFollowsIds() == null || humanProfile.getFollowsIds().size() == 0) {
			ArrayList<Long> profiles = databaseService.getProfileFollowsIds(humanProfile);
			humanProfile.setFollowsIds(profiles);
		}
	}

	/**
	 * Fill FollowedBy in HumanProfile.
	 * 
	 * @param humanProfile
	 */
	private void findFollowedByIds(HumanProfile humanProfile) {
		if (humanProfile.getFollowedByIds() == null || humanProfile.getFollowedByIds().size() == 0) {
			ArrayList<Long> profiles = databaseService.getProfileFollowedByIds(humanProfile);
			humanProfile.setFollowsIds(profiles);
		}
	}

	/**
	 * Link FollowedBy with HumanProfile.
	 * 
	 * @param humanProfile
	 */
	private void linkFollowedBy(HumanProfile humanProfile) {
		findFollowedByIds(humanProfile);
		if (humanProfile.getFollowedByIds() != null && humanProfile.getFollowedByIds().size() > 0) {
			List<HumanProfile> followedBy = new ArrayList<HumanProfile>();
			for (Long id : humanProfile.getFollowedByIds()) {
				followedBy.add(databaseService.getHumanProfile(id));
			}
			humanProfile.setFollowedBy(followedBy);
		}
	}

	/**
	 * Link Comments with HumanProfile.
	 * 
	 * @param humanProfile
	 */
	private void linkComments(HumanProfile humanProfile) {
		findProfileCommentsId(humanProfile);
		if (humanProfile.getAllCommentsId() != null && humanProfile.getAllCommentsId().size() > 0) {

			List<Comment> comments = new ArrayList<Comment>();
			for (Long commentId : humanProfile.getAllCommentsId()) {
				comments.add(databaseService.getComment(commentId.longValue()));
			}
			humanProfile.setComments(comments);
		}
	}

	/**
	 * Fill Comments in HumanProfile.
	 * 
	 * @param humanProfile
	 */
	private void findProfileCommentsId(HumanProfile humanProfile) {
		if (humanProfile.getAllCommentsId() == null || humanProfile.getAllCommentsId().size() == 0) {
			ArrayList<Long> allCommentsId = databaseService.getProfileAllComments(humanProfile);
			humanProfile.setAllCommentsId(allCommentsId);
		}
	}

}
