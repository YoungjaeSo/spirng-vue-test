<script setup>
import Header from './Header.vue';
import NavBar from './NavBar.vue';
import Footer from './Footer.vue';
</script>
<template>
  <div class="container">
    <Header />
    <NavBar />
    <div class="contentmy-5px-3">
      <slot></slot>
    </div>
    <Footer />
  </div>
</template>
