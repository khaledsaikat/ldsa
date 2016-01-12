package scribe.extractors;

import scribe.exceptions.OAuthParametersMissingException;
import scribe.model.AbstractRequest;
import scribe.model.ParameterList;
import scribe.utils.OAuthEncoder;
import scribe.utils.Preconditions;

/**
 * Default implementation of {@link BaseStringExtractor}. Conforms to OAuth 1.0a
 *
 * @author Pablo Fernandez
 *
 */
public class BaseStringExtractorImpl implements BaseStringExtractor {

    protected static final String AMPERSAND_SEPARATED_STRING = "%s&%s&%s";

    /**
     * {@inheritDoc}
     */
    public String extract(AbstractRequest request) {
        checkPreconditions(request);
        String verb = OAuthEncoder.encode(getVerb(request));
        String url = OAuthEncoder.encode(getUrl(request));
        String params = getSortedAndEncodedParams(request);
        return String.format(AMPERSAND_SEPARATED_STRING, verb, url, params);
    }

    protected String getVerb(AbstractRequest request) {
        return request.getVerb().name();
    }

    protected String getUrl(AbstractRequest request) {
        return request.getSanitizedUrl();
    }

    protected String getSortedAndEncodedParams(AbstractRequest request) {
        ParameterList params = new ParameterList();
        params.addAll(request.getQueryStringParams());
        params.addAll(request.getBodyParams());
        params.addAll(new ParameterList(request.getOauthParameters()));
        return params.sort().asOauthBaseString();
    }

    protected void checkPreconditions(AbstractRequest request) {
        Preconditions.checkNotNull(request, "Cannot extract base string from a null object");

        if (request.getOauthParameters() == null || request.getOauthParameters().size() <= 0) {
            throw new OAuthParametersMissingException(request);
        }
    }
}
