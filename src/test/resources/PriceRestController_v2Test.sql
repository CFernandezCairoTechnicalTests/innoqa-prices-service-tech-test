BEGIN;

--
-- Dumping data for table `brands_1`
--

INSERT INTO `brands_2` (`id`, `name`) VALUES
(1, 'ZARA');

COMMIT;

--
-- Dumping data for table `prices_1`
--

INSERT INTO `prices_2` (`id`, `end_date`, `curr`, `price`, `price_list`, `priority`, `product_id`, `start_date`, `brand_id`) VALUES
(1, '2021-01-01 04:59:59', 'EUR', 35.5, 1, 0, 35455, '2020-06-14 04:00:00', 1),
(2, '2020-06-14 22:30:00', 'EUR', 25.45, 2, 1, 35455, '2020-06-14 19:00:00', 1),
(3, '2020-06-15 15:30:00', 'EUR', 30.5, 3, 1, 35455, '2020-06-15 04:00:00', 1),
(4, '2021-01-01 04:59:59', 'EUR', 38.95, 4, 1, 35455, '2020-06-15 20:00:00', 1);

COMMIT;