//package com.copyroute.services.mongo;
//
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Page;
//import org.springframework.data.mongodb.repository.MongoRepository;
//
//
//
//public interface RssItemArchive_Repository
//	extends MongoRepository<RssItemArchive, String>
//{
//
//	Page<RssItemArchive> findDistinctByIdIgnoreCaseOrderByDateDesc(String id, Pageable pageable);
//
//	Page<RssItemArchive> findByTitle(String title, Pageable pageable);
//	Page<RssItemArchive> findDistinctByTitleIgnoreCaseLikeOrderByDateDesc(String title, Pageable pageable);
//
//	Page<RssItemArchive> findByDescriptionIgnoreCase(String description, Pageable pageable);
//	Page<RssItemArchive> findByDescriptionIgnoreCaseLike(String description, Pageable pageable);
//	Page<RssItemArchive> findDistinctByDescriptionIgnoreCaseLikeOrderByDateDesc(String description, Pageable pageable);
//
//	Page<RssItemArchive> findByFeed(String feed, Pageable pageable);
//	Page<RssItemArchive> findByFeedOrderByDateDesc(String feed, Pageable pageable);
//	Page<RssItemArchive> findDistinctByFeedIgnoreCaseLikeOrderByDateDesc(String feed, Pageable pageable);
//
//	Page<RssItemArchive> findDistinctByCompanyIgnoreCaseLikeOrderByDateDesc(String company, Pageable pageable);
//	Page<RssItemArchive> findDistinctByCategoryIgnoreCaseLikeOrderByDateDesc(String category, Pageable pageable);
//
//	Page<RssItemArchive> findDistinctByCompanyAndChannelIgnoreCaseOrderByDateDesc(String company, String channel, Pageable pageable);
//
//
//	 Page<RssItemArchive> findAllOrderByDateMonth( Pageable pageable);
//
//}
