package com.jacinto.fas.web.rest;

import com.jacinto.fas.service.LoteService;
import com.jacinto.fas.web.rest.errors.BadRequestAlertException;
import com.jacinto.fas.service.dto.LoteDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.jacinto.fas.domain.Lote}.
 */
@RestController
@RequestMapping("/api")
public class LoteResource {

    private final Logger log = LoggerFactory.getLogger(LoteResource.class);

    private static final String ENTITY_NAME = "jacintoFincaAdminServiceLote";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LoteService loteService;

    public LoteResource(LoteService loteService) {
        this.loteService = loteService;
    }

    /**
     * {@code POST  /lotes} : Create a new lote.
     *
     * @param loteDTO the loteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new loteDTO, or with status {@code 400 (Bad Request)} if the lote has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/lotes")
    public ResponseEntity<LoteDTO> createLote(@Valid @RequestBody LoteDTO loteDTO) throws URISyntaxException {
        log.debug("REST request to save Lote : {}", loteDTO);
        if (loteDTO.getId() != null) {
            throw new BadRequestAlertException("A new lote cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LoteDTO result = loteService.save(loteDTO);
        return ResponseEntity.created(new URI("/api/lotes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /lotes} : Updates an existing lote.
     *
     * @param loteDTO the loteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated loteDTO,
     * or with status {@code 400 (Bad Request)} if the loteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the loteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/lotes")
    public ResponseEntity<LoteDTO> updateLote(@Valid @RequestBody LoteDTO loteDTO) throws URISyntaxException {
        log.debug("REST request to update Lote : {}", loteDTO);
        if (loteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LoteDTO result = loteService.save(loteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, loteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /lotes} : get all the lotes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lotes in body.
     */
    @GetMapping("/lotes")
    public List<LoteDTO> getAllLotes() {
        log.debug("REST request to get all Lotes");
        return loteService.findAll();
    }

    /**
     * {@code GET  /lotes/:id} : get the "id" lote.
     *
     * @param id the id of the loteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the loteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lotes/{id}")
    public ResponseEntity<LoteDTO> getLote(@PathVariable Long id) {
        log.debug("REST request to get Lote : {}", id);
        Optional<LoteDTO> loteDTO = loteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(loteDTO);
    }

    /**
     * {@code DELETE  /lotes/:id} : delete the "id" lote.
     *
     * @param id the id of the loteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lotes/{id}")
    public ResponseEntity<Void> deleteLote(@PathVariable Long id) {
        log.debug("REST request to delete Lote : {}", id);

        loteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
