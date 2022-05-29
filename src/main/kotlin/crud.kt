class CRUD <A> {
    private var lastId : Int = 0
    private var elements : MutableMap<Int,Pair<A, Boolean>> = mutableMapOf()

    fun create (element: A) : Pair<Int, A?> {
        elements[lastId] = Pair(element,false)
        lastId+=1
        return Pair (lastId-1, elements[lastId-1]?.first)
    }
    fun read (id: Int): Pair<Int, A?> {
        val pair = elements[id]
        if (pair == null) {
            return Pair(id, null)
        }
        else if (pair.second) {
            return Pair(id, null)
        }
        return Pair (id, pair.first)
    }

    fun update (id: Int, element: A): Pair<Int, A?> {
        val pair = elements[id]
        if (pair == null) {
            return Pair(id, null)
        }
        else if (pair.second) {
            return Pair(id, null)
        }
        elements[id] = pair.copy(first = element)
        return Pair(id, elements[id]?.first)
    }
    fun delete (id: Int): Boolean {
        val pair = elements[id]
        if (pair == null) {
            return false
        }
        else if (pair.second) {
            return false
        }
        elements[id] = pair.copy(second = true)
        return true
    }
    fun restore (id: Int): Pair<Int, A?> {
        val pair = elements[id]
        if (pair == null) {
            return Pair(id, null)
        }
        else if (!pair.second) {
            return Pair(id, null)
        }
        elements[id] = pair.copy(second = false)
        return Pair(id, elements[id]?.first)
    }
    fun getList() : List<A> {
        return elements.values
            .filter { p -> !p.second }
            .map { p -> p.first }
    }



}

