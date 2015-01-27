package org.jpanda.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedirectRepository extends PagingAndSortingRepository<Redirect, Long>
{
    Redirect findByFromUrl(String from);
}
