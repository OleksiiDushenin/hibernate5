package dushenin.oleksii.hibernate5.id.service;

import dushenin.oleksii.hibernate5.id.entity.BookIdentityId;
import dushenin.oleksii.hibernate5.id.entity.BookSequenceId;
import dushenin.oleksii.hibernate5.id.entity.BookTableId;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by alexeydushenin
 * on 2/16/16.
 */
@Transactional
@Service
public class IdGenerationService {

    @Resource
    private EntityManager entityManager;

    public void saveBooksTableId(List<BookTableId> bookTableIds) {
        bookTableIds.stream().forEach(entityManager::persist);
    }

    public void saveBooksSequenceId(List<BookSequenceId> bookSequenceIds) {
        bookSequenceIds.stream().forEach(entityManager::persist);
    }

    public void saveBooksIdentityId(List<BookIdentityId> bookIdentityIds) {
        bookIdentityIds.stream().forEach(entityManager::persist);
    }

}
