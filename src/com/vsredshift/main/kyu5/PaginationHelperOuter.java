package com.vsredshift.main.kyu5;

import java.util.Arrays;
import java.util.List;

public class PaginationHelperOuter {
    public static void main(String[] args) {
        PaginationHelper<Character> helper = new PaginationHelper(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'), 3);
        System.out.println("items per page " + helper.itemsPerPage);
        System.out.println("item count " + helper.itemCount());
        System.out.println("page count " + helper.pageCount());
        int pageIndexIn = 0;
        System.out.println("item count for page "  + (pageIndexIn + 1) + ": " + helper.pageItemCount(pageIndexIn));
        int itemIndexIn = 2;
        System.out.println("item " + (itemIndexIn + 1) + " of " + helper.itemCount() + " is on page " + (helper.pageIndex(itemIndexIn) + 1));
    }

    public static class PaginationHelper<I> {
        private final List<I> collection;
        private final int itemsPerPage;

        /**
         * The constructor takes in an array of items and a integer indicating how many
         * items fit within a single page
         */
        public PaginationHelper(List<I> collection, int itemsPerPage) {
            this.collection = collection;
            this.itemsPerPage = itemsPerPage;
        }

        /**
         * returns the number of items within the entire collection
         */
        public int itemCount() {
            return collection.size();
        }

        /**
         * returns the number of pages
         */
        public int pageCount() {
//            return itemCount() % itemsPerPage == 0 ?
//                    itemCount() / itemsPerPage :
//                    itemCount() / itemsPerPage + 1;
            return (int) Math.ceil(itemCount() / (float) itemsPerPage);

        }

        /**
         * returns the number of items on the current page. page_index is zero based.
         * this method should return -1 for pageIndex values that are out of range
         */
        public int pageItemCount(int pageIndex) {
            if (pageIndex >= pageCount() || pageIndex < 0) return -1;
            if (pageIndex + 1 == pageCount()) return itemCount() - (pageIndex * itemsPerPage);
            return itemsPerPage;
        }

        /**
         * determines what page an item is on. Zero based indexes
         * this method should return -1 for itemIndex values that are out of range
         */
        public int pageIndex(int itemIndex) {
            if (itemIndex < 0 || itemIndex >= itemCount()) return -1;
            return itemIndex / itemsPerPage;
        }

    }
}
