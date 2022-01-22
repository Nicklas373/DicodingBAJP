package com.dicoding.moviecatalog.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ProductionCompaniesResponse(

	@field:SerializedName("production_companies")
	val productionCompanies: ArrayList<ProductionCompaniesListResponse>
)