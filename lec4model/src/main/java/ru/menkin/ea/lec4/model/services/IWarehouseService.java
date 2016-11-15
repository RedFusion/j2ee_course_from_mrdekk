package ru.menkin.ea.lec4.model.services;

import java.util.List;

import ru.menkin.ea.lec4.model.entities.Warehouse;

public interface IWarehouseService
{
	/**
	 * Сохраняет новый склад в базу данных может выдать ошибку, если таковой уже
	 * есть
	 * 
	 * Возвращает объект, прошедший сохранение в базе данных (у него например
	 * сгенерирован идентификатор)
	 */
	public Warehouse create(Warehouse warehouse);

	/**
	 * Удаляет склад из базы данных по идентификатору, возвращает либо удаленный
	 * объект (если таковой найден), либо null если объекта с таким
	 * идентификатором в базе не найдено
	 */
	public Warehouse delete(int id) throws Exception;

	/**
	 * Возвращает все ингредиенты из базы данных
	 */
	public List<Warehouse> findAll();

	/**
	 * Сохраняет изменения в объекте в базу данных
	 * 
	 * Возвращает измененный объект из БД (у него могут быть другие значения
	 * полей)
	 */
	public Warehouse update(Warehouse warehouse) throws Exception;

	/**
	 * Находит ингредиент по идентификатору
	 */
	public Warehouse findById(int id);

	public Warehouse findByName(String name);
}
