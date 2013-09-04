package org.mockito.configuration;

import org.mockito.internal.stubbing.defaultanswers.ReturnsSmartNulls;
import org.mockito.stubbing.Answer;

/**
 * This class has to be in this package in the test src or it will not be picked up.
 * If this class is found, you no longer need to set the answer type on all your @Mock fields.
 */
public class MockitoConfiguration extends DefaultMockitoConfiguration {

	public Answer<Object> getDefaultAnswer() {
		return new ReturnsSmartNulls();
	}

}
