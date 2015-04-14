//package com.copyroute.services.mongo;
//
//import com.copyroute.cdm.rss.RssItem;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.mongodb.repository.MongoRepository;
//
//
//public interface RssItemArchive2015_Repository
//	extends MongoRepository<RssItem, String>
//{
//
//	Page<RssItem> findDistinctByIdIgnoreCaseOrderByDateDesc(String id, Pageable pageable);
//
//	Page<RssItem> findByTitle(String title, Pageable pageable);
//	Page<RssItem> findDistinctByTitleIgnoreCaseLikeOrderByDateDesc(String title, Pageable pageable);
//
//	Page<RssItem> findByDescriptionIgnoreCase(String description, Pageable pageable);
//	Page<RssItem> findByDescriptionIgnoreCaseLike(String description, Pageable pageable);
//	Page<RssItem> findDistinctByDescriptionIgnoreCaseLikeOrderByDateDesc(String description, Pageable pageable);
//
//	Page<RssItem> findByFeed(String feed, Pageable pageable);
//	Page<RssItem> findByFeedOrderByDateDesc(String feed, Pageable pageable);
//	Page<RssItem> findDistinctByFeedIgnoreCaseLikeOrderByDateDesc(String feed, Pageable pageable);
//
//	Page<RssItem> findDistinctByCompanyIgnoreCaseLikeOrderByDateDesc(String company, Pageable pageable);
//	Page<RssItem> findDistinctByCategoryIgnoreCaseLikeOrderByDateDesc(String category, Pageable pageable);
//
//	Page<RssItem> findDistinctByCompanyAndChannelIgnoreCaseOrderByDateDesc(String company, String channel, Pageable pageable);
//	Page<RssItem> findAllOrderByDate(Pageable pageable);
//
//}
