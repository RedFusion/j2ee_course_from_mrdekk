package ru.menkin.ea.lec4.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.menkin.ea.lec4.model.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>
{

}
