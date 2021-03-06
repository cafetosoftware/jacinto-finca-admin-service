package com.jacinto.fas.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jacinto.fas.web.rest.TestUtil;

public class FincaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Finca.class);
        Finca finca1 = new Finca();
        finca1.setId(1L);
        Finca finca2 = new Finca();
        finca2.setId(finca1.getId());
        assertThat(finca1).isEqualTo(finca2);
        finca2.setId(2L);
        assertThat(finca1).isNotEqualTo(finca2);
        finca1.setId(null);
        assertThat(finca1).isNotEqualTo(finca2);
    }
}
