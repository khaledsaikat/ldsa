package de.due.ldsa.db.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import de.due.ldsa.model.OrganisationPlace;

/**
 * Created by  Romina
 */

@Accessor
public interface OrganisationPlaceAccessor {
    @Query("SELECT * FROM ldsa.organisationPlaces")
    Result<OrganisationPlace> getAll();

    @Query("SELECT * FROM ldsa.organisationPlaces WHERE snId = :id")
    Result<OrganisationPlace> getAllFromSocialNetwork(int snId);
}
