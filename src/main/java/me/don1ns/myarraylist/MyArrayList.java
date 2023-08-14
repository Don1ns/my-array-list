package me.don1ns.myarraylist;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * Реализация собственного ArrayList.
 * @Author Riyaz Karimullin
 */
public class MyArrayList<T>{
    private static final Object[] EMPTY_ELEMENTS = {};
    private Object[] elements;
    private int size;

    /**
     * Создает пустой список с указанной начальной емкостью.
     * @param capacity емкость внутреннего массива
     */
    public MyArrayList(int capacity) {
        if (capacity > 0) {
            this.elements = new Object[capacity];
        } else if (capacity == 0) {
            this.elements = EMPTY_ELEMENTS;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+ capacity);
        }
        this.size = 0;
    }

    /**
     * Создает пустой список.
     */
    public MyArrayList() {
        this.elements = EMPTY_ELEMENTS;
        this.size = 0;
    }

    /**
     * Возвращает количество элементов в списке.
     *
     * @return количество элементов
     */
    public int getSize() {
        return size;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент для добавления
     */
    public void add(T element) {
        provideCapacity(size + 1);
        elements[size++] = element;
    }

    /**
     * Добавляет элемент по указанному индексу.
     *
     * @param index   индекс для добавления
     * @param element элемент для добавления
     */
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Недопустимый индекс!");
        }
        provideCapacity(size + 1);
        System.arraycopy(elements, index,
                elements, index + 1,
                size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс элемента
     * @return элемент по указанному индексу
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Недопустимый индекс!");
        }
        return (T) elements[index];
    }

    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index индекс элемента для удаления
     * @return удаленный элемент
     */
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Недопустимый индекс!");
        }
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return removedElement;
    }

    /**
     * Очищает всю коллекцию, удаляя все элементы.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * Уменьшает емкость внутреннего массива до текущего размера списка.
     */
    public void trimToSize() {
        if (size < elements.length) {
            elements = (size == 0)
                    ? EMPTY_ELEMENTS
                    : Arrays.copyOf(elements, size);
        }
    }

    /**
     * Сортирует список с использованием алгоритма QuickSort.
     *
     * @param comparator компаратор для сравнения элементов
     */
    public void quickSort(Comparator<? super T> comparator) {
        quickSort(0, size - 1, comparator);
    }

    /**
     * Рекурсивно сортирует подмассивы внутри заданного диапазона.
     *
     * @param low        нижняя граница диапазона
     * @param high       верхняя граница диапазона
     * @param comparator компаратор для сравнения элементов
     */
    private void quickSort(int low, int high, Comparator<? super T> comparator) {
        if (low < high) {
            int pivotIndex = partition(low, high, comparator);
            quickSort(low, pivotIndex - 1, comparator);
            quickSort(pivotIndex + 1, high, comparator);
        }
    }

    /**
     * Разделяет массив на две части вокруг опорного элемента.
     *
     * @param low        нижняя граница диапазона
     * @param high       верхняя граница диапазона
     * @param comparator компаратор для сравнения элементов
     * @return индекс опорного элемента
     */
    private int partition(int low, int high, Comparator<? super T> comparator) {
        T pivot = (T) elements[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare((T) elements[j], pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    /**
     * Меняет местами элементы на позициях i и j в массиве.
     *
     * @param i позиция первого элемента для обмена
     * @param j позиция второго элемента для обмена
     */
    private void swap(int i, int j) {
        Object temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyArrayList<?> that = (MyArrayList<?>) o;
        return size == that.size && Arrays.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(elements);
        return result;
    }

    /**
     * Обеспечивает необходимую емкость внутреннего массива
     * @param minCapacity минимальная емкость
     */
    private void provideCapacity(int minCapacity) {
        if(minCapacity > elements.length) {
            if (elements == EMPTY_ELEMENTS) {
                elements = Arrays.copyOf(elements, minCapacity);
            } else {
                int oldCapacity = elements.length;
                int newCapacity = (oldCapacity == 1)
                        ? 2
                        : oldCapacity + (oldCapacity >> 1);
                elements = Arrays.copyOf(elements, newCapacity);
            }
        }
    }
}
